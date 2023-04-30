package com.admiralbot.orca.model.discord.components;

import com.admiralbot.orca.model.discord.guild.Emoji;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URL;

public record ButtonComponent(
        @JsonProperty(value = "type", required = true) MessageComponentType type,
        @JsonProperty(value = "style", required = true) ButtonStyle style,
        @JsonProperty("label") String label,
        @JsonProperty("emoji") Emoji emoji,
        @JsonProperty("custom_id") String customId,
        @JsonProperty("url") URL url,
        @JsonProperty("disabled") Boolean isDisabled
) implements MessageComponent {}
