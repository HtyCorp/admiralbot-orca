package com.admiralbot.orca.auth;

import lombok.extern.log4j.Log4j2;
import org.bouncycastle.math.ec.rfc8032.Ed25519;
import software.amazon.cloudwatchlogs.emf.model.Unit;

import java.time.Duration;
import java.time.Instant;
import java.util.HexFormat;
import java.util.List;
import java.util.Map;

import static com.admiralbot.orca.metrics.EmbeddedMetrics.booleanMetric;
import static com.admiralbot.orca.metrics.EmbeddedMetrics.numberMetric;
import static java.nio.charset.StandardCharsets.UTF_8;

@Log4j2
public class InteractionAuthenticator {

    private static final Duration MAX_CLOCK_SKEW_ALLOWED = Duration.ofMinutes(15);

    private final boolean enableClockSkewCheck;
    private final List<byte[]> discordAppKeySet;

    public InteractionAuthenticator(boolean enableClockSkewCheck, List<String> discordAppKeySet) {
        this.enableClockSkewCheck = enableClockSkewCheck;
        this.discordAppKeySet = discordAppKeySet.stream().map(HexFormat.of()::parseHex).toList();
    }

    public boolean authenticateTimestampedMessage(String signatureHex, String timestamp, String requestBody) {
        if (signatureHex == null || timestamp == null || requestBody == null) {
            return false;
        }

        log.trace("Starting signature validation");
        var message = (timestamp + requestBody).getBytes(UTF_8);
        var validSignature = discordAppKeySet.stream().anyMatch(key -> authenticateMessage(signatureHex, key, message));

        log.trace("Starting timestamp validation");
        // Discord doesn't strictly require this, but not validating clock skew on timestamp feels weird.
        var validTimestamp = validateTimestamp(timestamp);

        log.trace("Starting final metric publish");
        booleanMetric("ValidSignature", Map.of(), validSignature);
        booleanMetric("ValidTimestamp", Map.of(), validTimestamp);

        return validSignature && (!enableClockSkewCheck || validTimestamp);
    }

    private boolean validateTimestamp(String timestamp) {
        try {
            var instant = Instant.ofEpochSecond(Long.parseLong(timestamp));
            var timestampAge = Duration.between(instant, Instant.now()).abs();
            numberMetric("InteractionTimestampAge", Map.of(), timestampAge.toMillis(), Unit.MILLISECONDS);
            if (timestampAge.compareTo(MAX_CLOCK_SKEW_ALLOWED) > 0) {
                log.warn("Timestamp age ({} seconds) exceeded maximum", timestampAge.toSeconds());
                return false;
            }
            return true;
        } catch (RuntimeException e) {
            summaryExceptionWarn("Interaction timestamp parse threw an error", e);
            return false;
        }
    }

    private boolean authenticateMessage(String signatureHex, byte[] publicKeyBytes, byte[] messageBytes) {
        try {
            var signatureBytes = HexFormat.of().parseHex(signatureHex);
            return Ed25519.verify(
                    signatureBytes, 0,
                    publicKeyBytes, 0,
                    messageBytes, 0, messageBytes.length
            );
        } catch (RuntimeException e) {
            summaryExceptionWarn("Interaction body verify threw an error", e);
            return false;
        }
    }

    // Logs a brief WARN message for an exception rather than the full stack trace.
    // Useful for this class since it's auth code and therefore spam-sensitive.
    private void summaryExceptionWarn(String msg, Exception e) {
        log.warn("{} ({}: {})", msg, e.getClass().getName(), e.getMessage());
    }
}
