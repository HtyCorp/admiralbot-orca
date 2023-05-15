package com.admiralbot.orca.ops;

import com.admiralbot.orca.ops.commandsync.ApplicationCommandParams;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;

public class DiscordApiClient {

    private static final String DISCORD_URI_BASE = "https://discord.com/api/v10";
    private static final String USER_AGENT = "DiscordBot (https://github.com/HtyCorp/admiralbot-orca, 0.1)";

    private static final ObjectMapper JSON = new ObjectMapper();

    private final HttpClient httpClient;
    private final String authorizationToken;

    public DiscordApiClient(HttpClient httpClient, String authorizationToken) {
        this.httpClient = httpClient;
        this.authorizationToken = authorizationToken;
    }

    public void overwriteGlobalCommands(String applicationId, List<ApplicationCommandParams> commands) {
        var url = String.format("%s/applications/%s/commands", DISCORD_URI_BASE, applicationId);
        var payload = serialize(commands);
        makeRequest(url, payload);
    }

    public void overwriteGuildCommands(String applicationId, String guildId, List<ApplicationCommandParams> commands) {
        var url = String.format("%s/applications/%s/guilds/%s/commands", DISCORD_URI_BASE, applicationId, guildId);
        var payload = serialize(commands);
        makeRequest(url, payload);
    }

    private String serialize(Object payload) {
        try {
            return JSON.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialise command payload", e);
        }
    }

    private void makeRequest(String url, String payload) {
        var request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .PUT(BodyPublishers.ofString(payload))
                .header("Content-Type", "application/json")
                .header("User-Agent", USER_AGENT)
                .header("Authorization", "Bot " + authorizationToken)
                .build();
        System.out.println("Request: " + payload);
        try {
            var response = httpClient.send(request, BodyHandlers.ofString());
            System.out.println("Response: " + response.body());
            if (response.statusCode() != 200) {
                throw new RuntimeException("Non-200 response from Discord API (got " + response.statusCode() + ")");
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Discord API HTTP call failed", e);
        }
    }
}
