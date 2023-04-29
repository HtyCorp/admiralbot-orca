package com.admiralbot.orca.auth;

import com.admiralbot.orca.config.AppConfigClient;
import org.bouncycastle.math.ec.rfc8032.Ed25519;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.SecureRandom;
import java.util.HexFormat;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class InteractionEndpointAuthenticatorTest {

    private static final HexFormat HEX = HexFormat.of();

    @Mock
    AppConfigClient mockAppConfigClient;

    private InteractionEndpointAuthenticator authenticator;

    @BeforeEach
    public void setup() {
        var publicKey = HEX.parseHex("6fc8a9c31cc8f58788307bce5e0fea2b2c5a1002401ea5cc66987471d0af0a6c");
        Mockito.when(mockAppConfigClient.getDiscordAppPublicKeyBytes()).thenReturn(publicKey);
        authenticator = new InteractionEndpointAuthenticator(mockAppConfigClient);
    }

    @Test
    public void testValidStringBody() {
        // Generated using `createTestVectors()` below
        var signature = "110542650f2f4d7afaa86a9ff225134962ecb91c8efd9dc01f68e1f543e70d8b" +
                "f6cd10ac447a604f229084376c3ec9d09b6fc75c4a3111fe0e9d8b76036a0605";
        var timestamp = "2023-04-30";
        var body = "Example timestamped request body";

        var isValid = authenticator.authenticateTimestampedMessage(signature, timestamp, body);

        assertTrue(isValid);
    }

    @Test
    public void testStringBodySignedByWrongKey() {
        // Same as above, but using new public key - should be considered invalid
        var signature = "5fffdc6af173788b5458397d59f84eeddded3717efb2d5bd4ae75ca3579d83c5" +
                "36910aedf9fab50e8524f52f0494ef1260a14e7882aa3e942cdd87352556dc06";
        var timestamp = "2023-04-30";
        var body = "Example timestamped request body";

        var isValid = authenticator.authenticateTimestampedMessage(signature, timestamp, body);

        assertFalse(isValid);
    }

    @Test
    @Disabled
    public void createTestVectors() {
        var rand = new SecureRandom();
        var privateKey = new byte[32];
        var publicKey = new byte[32];
        Ed25519.generatePrivateKey(rand, privateKey);
        Ed25519.generatePublicKey(privateKey, 0, publicKey, 0);

        var message = "2023-04-30Example timestamped request body".getBytes(UTF_8);
        var signature = new byte[64];
        Ed25519.sign(privateKey, 0,
                message, 0, message.length,
                signature, 0);

        System.out.println("Public key hex: " + HEX.formatHex(publicKey));
        System.out.println("Signature hex: " + HEX.formatHex(signature));
    }

}
