package com.admiralbot.orca.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AppConfigClientTest {

    @Mock
    private HttpClient mockHttpClient;

    @Mock
    private HttpResponse<String> mockHttpResponse;

    private AppConfigClient appConfigClient;

    @BeforeEach
    public void setup() throws Exception {
        when(mockHttpClient.send(any(), ArgumentMatchers.<BodyHandler<String>>any())).thenReturn(mockHttpResponse);
        appConfigClient = new AppConfigClient(mockHttpClient);
    }

    @Test
    public void testValidPublicKeyHexParse() throws Exception {
        var keyHex = "000102030405060708090a0b0c0d0e0f101112131415161718191a1b1c1d1e1f";
        when(mockHttpResponse.statusCode()).thenReturn(200);
        when(mockHttpResponse.body()).thenReturn(keyHex);

        var keyBytes = appConfigClient.getDiscordAppPublicKeyBytes();

        var httpRequest = ArgumentCaptor.forClass(HttpRequest.class);
        verify(mockHttpClient).send(httpRequest.capture(), any());
        assertEquals("http://localhost:2772/applications/AdmiralBot/environments/OrcaLambda/configurations/DiscordAppPublicKey",
                httpRequest.getValue().uri().toString());
        assertArrayEquals(new byte[] {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31},
                keyBytes);
    }
}