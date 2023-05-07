package com.admiralbot.orca.discord.model.message;

import com.admiralbot.orca.discord.model.guild.Emoji;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Reaction(
        @JsonProperty(value = "count", required = true) int count,
        @JsonProperty(value = "me", required = true) boolean isByMe,
        @JsonProperty(value = "emoji", required = true) Emoji emoji
) {}
