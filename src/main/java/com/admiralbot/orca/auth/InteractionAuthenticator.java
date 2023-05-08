package com.admiralbot.orca.auth;

import lombok.extern.log4j.Log4j2;
import org.bouncycastle.math.ec.rfc8032.Ed25519;

import java.util.HexFormat;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

@Log4j2
public class InteractionAuthenticator {

    private static final HexFormat HEX = HexFormat.of();

    private final List<byte[]> discordAppKeySet;

    public InteractionAuthenticator(List<String> discordAppKeySet) {
        this.discordAppKeySet = discordAppKeySet.stream().map(HexFormat.of()::parseHex).toList();
    }

    public boolean authenticateTimestampedMessage(String signatureHex, String timestamp, String requestBody) {
        if (signatureHex == null || timestamp == null || requestBody == null) {
            return false;
        }

        var signature = HEX.parseHex(signatureHex);
        var message = (timestamp + requestBody).getBytes(UTF_8);

        return discordAppKeySet.stream().anyMatch(key -> authenticateMessage(signature, key, message));
    }

    private boolean authenticateMessage(byte[] signatureBytes, byte[] publicKeyBytes, byte[] messageBytes) {
        try {
            return Ed25519.verify(
                    signatureBytes, 0,
                    publicKeyBytes, 0,
                    messageBytes, 0, messageBytes.length
            );
        } catch (RuntimeException e) {
            log.warn("Interaction body verify threw an error", e);
            return false;
        }
    }
}
