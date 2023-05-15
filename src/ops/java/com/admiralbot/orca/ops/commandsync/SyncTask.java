package com.admiralbot.orca.ops.commandsync;

import com.admiralbot.orca.ops.DiscordApiClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.sts.StsClient;

import java.io.IOException;
import java.net.http.HttpClient;

public class SyncTask {

    public static void main(String[] args) throws IOException {
        System.setProperty("software.amazon.awssdk.http.service.impl",
                "software.amazon.awssdk.http.urlconnection.UrlConnectionSdkHttpService");
        var task = new SyncTask(args[0], args[1], args[2]);
        task.sync();
    }

    private static final ObjectMapper JSON = new ObjectMapper();

    private final AwsCredentialsProvider credentialsProvider = DefaultCredentialsProvider.create();

    private final String accountId;
    private final String applicationId;
    private final String guildId;

    public SyncTask(String accountId, String applicationId, String guildId) {
        this.accountId = accountId;
        this.applicationId = applicationId;
        this.guildId = guildId;
    }

    public void sync() throws IOException {
        checkAccountId();
        var token = getBotToken();
        var discordClient = new DiscordApiClient(HttpClient.newHttpClient(), token);
        if (guildId.equals("GLOBAL")) {
            discordClient.overwriteGlobalCommands(applicationId, Commands.COMMANDS);
        } else {
            discordClient.overwriteGuildCommands(applicationId, guildId, Commands.COMMANDS);
        }
    }

    private void checkAccountId() {
        var stsClient = StsClient.builder()
                .credentialsProvider(credentialsProvider)
                .build();
        var actualAccountId = stsClient.getCallerIdentity().account();
        if (!actualAccountId.equals(accountId)) {
            throw new IllegalArgumentException("Expected account ID " + accountId + " but got " + actualAccountId);
        }
    }

    private String getBotToken() throws IOException {
        var secretsManagerClient = SecretsManagerClient.builder()
                .credentialsProvider(credentialsProvider)
                .build();
        var content = secretsManagerClient.getSecretValue(r -> r.secretId("BotAuthorizationToken")).secretString();
        var object = (ObjectNode) JSON.readTree(content);
        var applicationIdFromSecret = object.get("applicationId").textValue();
        if (!applicationIdFromSecret.equals(applicationId)) {
            throw new IllegalArgumentException("Expected application ID " + applicationId + " but got " + applicationIdFromSecret);
        }
        return object.get("token").textValue();
    }
}
