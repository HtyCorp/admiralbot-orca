package com.admiralbot.orca.discord.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record VoiceRegion(
        @JsonProperty("id") String id, // NOT a snowflake
        @JsonProperty("name") String name,
        @JsonProperty("optimal") boolean isOptimal,
        @JsonProperty("deprecated") boolean isDeprecated,
        @JsonProperty("custom") boolean isCustom
) {}
