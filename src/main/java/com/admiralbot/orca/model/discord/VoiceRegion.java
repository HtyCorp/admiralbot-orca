package com.admiralbot.orca.model.discord;

import com.fasterxml.jackson.annotation.JsonProperty;

public record VoiceRegion(
        @JsonProperty("id") String id, // NOT a snowflake
        @JsonProperty("name") String name,
        @JsonProperty("optimal") boolean isOptimal,
        @JsonProperty("deprecated") boolean isDeprecated,
        @JsonProperty("custom") boolean isCustom
) {}
