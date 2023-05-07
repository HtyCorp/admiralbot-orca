package com.admiralbot.orca.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.appconfigdata.AppConfigDataClient;
import software.amazon.awssdk.services.appconfigdata.model.GetLatestConfigurationRequest;
import software.amazon.awssdk.services.appconfigdata.model.GetLatestConfigurationResponse;
import software.amazon.awssdk.services.appconfigdata.model.StartConfigurationSessionRequest;
import software.amazon.awssdk.services.appconfigdata.model.StartConfigurationSessionResponse;
import software.amazon.awssdk.utils.builder.CopyableBuilder;
import software.amazon.awssdk.utils.builder.ToCopyableBuilder;

import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AppConfigTest {

    @Mock
    private AppConfigDataClient mockAppConfigClient;

    @Captor
    private ArgumentCaptor<Consumer<StartConfigurationSessionRequest.Builder>> startConfigurationSession;

    @Captor
    private ArgumentCaptor<Consumer<GetLatestConfigurationRequest.Builder>> getLatestConfiguration;

    private AppConfig appConfig;

    @BeforeEach
    public void setup() {
        appConfig = new AppConfig(mockAppConfigClient);
    }

    @Test
    public void testValidPublicKeyHexParse() {
        var configData = """
                {
                  "authorizedKeys": [
                    {"publicKeyHex": "000102030405060708090a0b0c0d0e0f101112131415161718191a1b1c1d1e1f"}
                  ]
                }
                """;
        when(mockAppConfigClient.startConfigurationSession(any(Consumer.class))).thenReturn(StartConfigurationSessionResponse.builder()
                .initialConfigurationToken("firsttoken")
                .build());
        when(mockAppConfigClient.getLatestConfiguration(any(Consumer.class))).thenReturn(GetLatestConfigurationResponse.builder()
                .configuration(SdkBytes.fromUtf8String(configData))
                .nextPollConfigurationToken("secondtoken")
                .nextPollIntervalInSeconds(30)
                .build());

        var keysConfig = appConfig.getAuthorizedDiscordAppKeys();

        verify(mockAppConfigClient).startConfigurationSession(startConfigurationSession.capture());
        assertBuilder(startConfigurationSession, StartConfigurationSessionRequest.builder(), r -> {
            assertEquals("AdmiralBot", r.applicationIdentifier());
            assertEquals("OrcaLambda", r.environmentIdentifier());
            assertEquals("AuthorizedDiscordAppKeys", r.configurationProfileIdentifier());
        });
        verify(mockAppConfigClient).getLatestConfiguration(getLatestConfiguration.capture());
        assertBuilder(getLatestConfiguration, GetLatestConfigurationRequest.builder(), r -> {
            assertEquals("firsttoken", r.configurationToken());
        });

        assertEquals(1,
                keysConfig.authorizedKeys().size());
        assertEquals("000102030405060708090a0b0c0d0e0f101112131415161718191a1b1c1d1e1f",
                keysConfig.authorizedKeys().get(0).publicKeyHex());
    }

    // Hard to argue that the convenience of builder-syntax is worth all this trouble. Have to think about either
    // coming up with good generic test utilities, or using standard non-builder syntax.
    private <B extends CopyableBuilder<B, T>, T extends ToCopyableBuilder<B, T>> void assertBuilder(ArgumentCaptor<Consumer<B>> testCaptor,
                                                                                                    B builder, Consumer<T> testBlock) {
        var t = builder.applyMutation(testCaptor.getValue()).build();
        testBlock.accept(t);
    }
}
