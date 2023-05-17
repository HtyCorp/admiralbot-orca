package com.admiralbot.orca.auth;

import org.bouncycastle.math.ec.rfc8032.Ed25519;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.SecureRandom;
import java.util.HexFormat;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class InteractionAuthenticatorTest {

    private InteractionAuthenticator authenticator;

    @BeforeEach
    public void setup() {
        authenticator = new InteractionAuthenticator(false,
                List.of("a38ccb99ea4dcf04fe252d1c19c7cec8fe04ec5161b9e782054d2dde4874e613"));
    }

    @Test
    public void testAcceptValidSignedMessage() {
        // Generated using `createTestVectors()` below
        var signature = "3d6e3d7200912b11c808868bfabf63d349975eb04acd7ef0cfd3f37d2a85ddcc" +
                "a87c56d3335f5cb8361ccf1e8cc4baf8228a9de6d12edbeb9e7495592d838305";
        var timestamp = "1684281600";
        var body = "Example timestamped request body";

        var isValid = authenticator.authenticateTimestampedMessage(signature, timestamp, body);

        assertTrue(isValid);
    }

    @Test
    public void testRejectsMessageWithOldTimestampIfSkewCheckEnabled() {
        // Same as first test, but with clock skew check Enabled so that the same passing test should fail
        authenticator = new InteractionAuthenticator(true,
                List.of("a38ccb99ea4dcf04fe252d1c19c7cec8fe04ec5161b9e782054d2dde4874e613"));
        var signature = "3d6e3d7200912b11c808868bfabf63d349975eb04acd7ef0cfd3f37d2a85ddcc" +
                "a87c56d3335f5cb8361ccf1e8cc4baf8228a9de6d12edbeb9e7495592d838305";
        var timestamp = "1684281600";
        var body = "Example timestamped request body";

        var isValid = authenticator.authenticateTimestampedMessage(signature, timestamp, body);

        assertFalse(isValid);
    }

    @Test
    public void testRejectsMessageIfInputsNull() {
        var signature = "3d6e3d7200912b11c808868bfabf63d349975eb04acd7ef0cfd3f37d2a85ddcc" +
                "a87c56d3335f5cb8361ccf1e8cc4baf8228a9de6d12edbeb9e7495592d838305";
        var timestamp = "1684281600";
        var body = "Example timestamped request body";

        assertFalse(authenticator.authenticateTimestampedMessage(null, timestamp, body));
        assertFalse(authenticator.authenticateTimestampedMessage(signature, null, body));
        assertFalse(authenticator.authenticateTimestampedMessage(signature, timestamp, null));
    }

    @Test
    public void testRejectsMessageSignedByWrongKey() {
        // Same as first test, but uses a different signing key - should be considered invalid
        var signature = "7827840d361ac2e57353f465955572cadaace27fab77d288c829153bc66f01b2" +
                "79d551ac8dfc6e099d16df79698ca2bd01b5b54587cf93a2de6da9a8ba85b309";
        var timestamp = "1684281600";
        var body = "Example timestamped request body";

        var isValid = authenticator.authenticateTimestampedMessage(signature, timestamp, body);

        assertFalse(isValid);
    }

    @Test
    public void testRejectsNonEpochTimestamp() {
        var signature = "3d6e3d7200912b11c808868bfabf63d349975eb04acd7ef0cfd3f37d2a85ddcc" +
                "a87c56d3335f5cb8361ccf1e8cc4baf8228a9de6d12edbeb9e7495592d838305";
        var timestamp = "2023-05-17T00:00:00Z";
        var body = "Example timestamped request body";

        var isValid = authenticator.authenticateTimestampedMessage(signature, timestamp, body);

        assertFalse(isValid);
    }

    @Test
    public void testRejectsInvalidHexSignature() {
        var signature = "blahblahasdf!$%#@%";
        var timestamp = "1684281600";
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

        // arbitrary timestamp: 2023-05-17T00:00:00Z to epoch
        var message = "1684281600Example timestamped request body".getBytes(UTF_8);
        var signature = new byte[64];
        Ed25519.sign(privateKey, 0,
                message, 0, message.length,
                signature, 0);

        System.out.println("Public key hex: " + HexFormat.of().formatHex(publicKey));
        System.out.println("Signature hex: " + HexFormat.of().formatHex(signature));
    }

}
