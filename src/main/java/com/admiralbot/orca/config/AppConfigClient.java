package com.admiralbot.orca.config;

import com.admiralbot.orca.config.model.AuthorizedDiscordAppKeysConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import lombok.extern.log4j.Log4j2;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.HexFormat;

import static java.nio.charset.StandardCharsets.UTF_8;

@Log4j2
public class AppConfigClient {

    private static final String APP_NAME = "AdmiralBot";
    private static final String ENV_NAME = "OrcaLambda";

    private static final ObjectMapper JSON = new ObjectMapper();
    private static final ObjectReader AUTHORIZED_KEYS_READER = JSON.readerFor(AuthorizedDiscordAppKeysConfig.class);

    private final HttpClient httpClient;

    public AppConfigClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public AuthorizedDiscordAppKeysConfig getAuthorizedDiscordAppKeys() {
        return readConfigJson("AuthorizedDiscordAppKeys", AUTHORIZED_KEYS_READER);
    }

    private <T> T readConfigJson(String configName, ObjectReader reader) {
        try {
            var json = readConfigString(configName);
            return reader.readValue(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse config JSON for config " + configName, e);
        }
    }

    private String readConfigString(String configName) {
        var uri = "http://localhost:2772/applications/" + APP_NAME + "/environments/" + ENV_NAME + "/configurations/" + configName;
        var httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(uri))
                .build();
        try {
            var startTime = System.nanoTime();
            var response = httpClient.send(httpRequest, BodyHandlers.ofString(UTF_8));
            var latencyMillis = (System.nanoTime() - startTime) / 1_000_000L;
            log.info("AppConfig response: statusCode={}, latency={}, uri={}",
                    response.statusCode(), latencyMillis, uri);

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
