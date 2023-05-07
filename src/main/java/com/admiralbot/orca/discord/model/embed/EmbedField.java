package com.admiralbot.orca.discord.model.embed;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EmbedField(
        @JsonProperty(value = "name", required = true) String name,
        @JsonProperty(value = "value", required = true) String value,
        @JsonProperty("inline") boolean isInline
) {}
