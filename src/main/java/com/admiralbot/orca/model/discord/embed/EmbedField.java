package com.admiralbot.orca.model.discord.embed;

import com.fasterxml.jackson.annotation.JsonProperty;

public record EmbedField(
        @JsonProperty(value = "name", required = true) String name,
        @JsonProperty(value = "value", required = true) String value,
        @JsonProperty("inline") boolean isInline
) {}
