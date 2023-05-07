package com.admiralbot.orca.auth;

import com.admiralbot.orca.config.AppConfig;
import lombok.extern.log4j.Log4j2;
import org.bouncycastle.math.ec.rfc8032.Ed25519;

import java.util.HexFormat;

import static java.nio.charset.StandardCharsets.UTF_8;

@Log4j2
public class InteractionAuthenticator {

    private static final HexFormat HEX = HexFormat.of();

    private final AppConfig appConfig;

    public InteractionAuthenticator(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    public boolean authenticateTimestampedMessage(String signatureHex, String timestamp, String requestBody) {
        if (signatureHex == null || timestamp == null || requestBody == null) {
            return false;
        }

        var signature = HEX.parseHex(signatureHex);
        var message = (timestamp + requestBody).getBytes(UTF_8);
        var keysConfig = appConfig.getAuthorizedDiscordAppKeys();

        return keysConfig.authorizedKeys().stream()
                .map(key -> HEX.parseHex(key.publicKeyHex()))
                .anyMatch(keyBytes -> authenticateMessage(signature, keyBytes, message));
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
