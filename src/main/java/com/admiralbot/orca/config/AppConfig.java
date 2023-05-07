package com.admiralbot.orca.config;

import com.admiralbot.orca.config.model.AuthorizedDiscordAppKeysConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import lombok.extern.log4j.Log4j2;
import software.amazon.awssdk.services.appconfigdata.AppConfigDataClient;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

@Log4j2
public class AppConfig {

    private static final String APP_NAME = "AdmiralBot";
    private static final String ENV_NAME = "OrcaLambda";
    private static final int CACHE_TTL_SECONDS = 30;
    private static final int CACHE_TTL_BUFFER_SECONDS = 1;

    private static final ObjectMapper JSON = new ObjectMapper();
    // I'm hoping that initializing these statically will move some reflection cost into the SnapStart init phase
    private static final ObjectReader AUTHORIZED_KEYS_READER = JSON.readerFor(AuthorizedDiscordAppKeysConfig.class);

    private final Map<String, ConfigVersion<?>> versionCache = new HashMap<>();
    private record ConfigVersion<V>(
            String sessionToken,
            Instant needsRefreshAfter,
            V versionValue
    ) {}

    private final AppConfigDataClient appConfigClient;

    public AppConfig(AppConfigDataClient appConfigClient) {
        this.appConfigClient = appConfigClient;
    }

    public AuthorizedDiscordAppKeysConfig getAuthorizedDiscordAppKeys() {
        return getValue("AuthorizedDiscordAppKeys", AUTHORIZED_KEYS_READER, AuthorizedDiscordAppKeysConfig.class);
    }

    // Note: ObjectReader isn't generified, so it will just error on a bad class - not much safety there
    @SuppressWarnings("unused")
    private <V> V getValue(String configName, ObjectReader vReader, Class<V> vClass) {

        var version = versionCache.computeIfAbsent(configName, name -> {
            log.info("(profile={}) Version not found in cache, starting config session", configName);
            var session = appConfigClient.startConfigurationSession(r -> r
                    .applicationIdentifier(APP_NAME)
                    .environmentIdentifier(ENV_NAME)
                    .configurationProfileIdentifier(configName)
                    .requiredMinimumPollIntervalInSeconds(CACHE_TTL_SECONDS));
            return new ConfigVersion<>(session.initialConfigurationToken(),
                    Instant.MIN, null);
        });

        var value = vClass.cast(version.versionValue());

        if (Instant.now().isAfter(version.needsRefreshAfter())) {
            log.info("(profile={}) Cached version is uninitialized or expired, refreshing now", configName);
            var refresh = appConfigClient.getLatestConfiguration(r -> r.configurationToken(version.sessionToken()));
            if (refresh.configuration().asByteArrayUnsafe().length > 0) {
                log.info("(profile={}) Refresh response has new data, updating cached version", configName);
                try {
                    value = vClass.cast(vReader.readValue(refresh.configuration().asByteArrayUnsafe()));
                } catch (IOException e) {
                    throw new RuntimeException("Failed to parse config JSON for config", e);
                }
            } else {
                log.info("(profile={}) Empty refresh response, retaining current cached value", configName);
            }
            var nextRefresh = Instant.now().plus(refresh.nextPollIntervalInSeconds() + CACHE_TTL_BUFFER_SECONDS, ChronoUnit.SECONDS);
            versionCache.put(configName, new ConfigVersion<>(refresh.nextPollConfigurationToken(), nextRefresh, value));
        }

        return value;
    }
}
