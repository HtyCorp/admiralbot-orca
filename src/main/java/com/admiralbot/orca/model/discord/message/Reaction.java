package com.admiralbot.orca.model.discord.message;

import com.admiralbot.orca.model.discord.guild.Emoji;
import com.fasterxml.jackson.annotation.JsonProperty;

public record Reaction(
        @JsonProperty(value = "count", required = true) int count,
        @JsonProperty(value = "me", required = true) boolean isByMe,
        @JsonProperty(value = "emoji", required = true) Emoji emoji
) {}