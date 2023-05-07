package com.admiralbot.orca.config.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record AuthorizedDiscordAppKeysConfig(
        @JsonProperty(value = "authorizedKeys", required = true) List<AuthorizedKey> authorizedKeys
) {}
