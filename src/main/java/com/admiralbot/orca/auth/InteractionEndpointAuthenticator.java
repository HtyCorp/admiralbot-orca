package com.admiralbot.orca.auth;

import com.admiralbot.orca.config.AppConfigClient;
import org.bouncycastle.math.ec.rfc8032.Ed25519;

import java.util.HexFormat;

import static java.nio.charset.StandardCharsets.UTF_8;

public class InteractionEndpointAuthenticator {

    private static final HexFormat HEX = HexFormat.of();

    private final AppConfigClient appConfigClient;

    public InteractionEndpointAuthenticator(AppConfigClient appConfigClient) {
        this.appConfigClient = appConfigClient;
    }

    public boolean authenticateTimestampedMessage(String signatureHex, String timestamp, String requestBody) {
        var publicKeyBytes = appConfigClient.getDiscordAppPublicKeyBytes();
        var signatureBytes = HEX.parseHex(signatureHex);
        var timestampedBody = (timestamp + requestBody).getBytes(UTF_8);
        return Ed25519.verify(
                signatureBytes, 0, // signature (no array offset)
                publicKeyBytes, 0, // public key (no array offset)
                timestampedBody, 0, timestampedBody.length // message (no offset, length required)
        );
    }

}
