package com.admiralbot.orca.config;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.HexFormat;

import static java.nio.charset.StandardCharsets.UTF_8;

public class AppConfigClient {

    private static final String APP_NAME = "AdmiralBot";
    private static final String ENV_NAME = "OrcaLambda";

    private static final HexFormat HEX = HexFormat.of();

    private final HttpClient httpClient;

    public AppConfigClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public byte[] getDiscordAppPublicKeyBytes() {
        var publicKeyHexString = readConfigString("DiscordAppPublicKey");
        return HEX.parseHex(publicKeyHexString);
    }

    private String readConfigString(String configName) {
        var uri = "http://localhost:2772/applications/" + APP_NAME + "/environments/" + ENV_NAME + "/configurations/" + configName;
        var httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(uri))
                .build();
        try {
            var response = httpClient.send(httpRequest, BodyHandlers.ofString(UTF_8));
            // I don't actually *know* that the Lambda AppConfig extensions only returns 200, but I'm hoping.
            if (response.statusCode() != 200) {
                throw new RuntimeException("Non-200 status code: " + response.statusCode());
            }
            return response.body();
        } catch (Exception e) {
            String loggedConfigName = APP_NAME + "/" + ENV_NAME + "/" + configName;
            throw new RuntimeException("Failed to read config key '" + loggedConfigName + "'", e);
        }
    }
}
