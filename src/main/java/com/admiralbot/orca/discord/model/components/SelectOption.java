package com.admiralbot.orca.discord.model.components;

import com.admiralbot.orca.discord.model.guild.Emoji;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SelectOption(
        @JsonProperty(value = "label", required = true) String label,
        @JsonProperty(value = "value", required = true) String value,
        @JsonProperty("description") String description,
        @JsonProperty("emoji") Emoji emoji,
        @JsonProperty("default") Boolean isSelectedByDefault
) {}
