package com.admiralbot.orca.discord.model.components;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TextInputComponent(
        @JsonProperty(value = "type", required = true) MessageComponentType type,
        @JsonProperty(value = "custom_id", required = true) String customId,
        @JsonProperty(value = "style", required = true) TextInputStyle style,
        @JsonProperty(value = "label", required = true) String label,
        @JsonProperty("min_length") Integer minLength,
        @JsonProperty("max_length") Integer maxLength,
        @JsonProperty("required") Boolean isRequired,
        @JsonProperty("placeholder") String placeholderText
) implements MessageComponent {}
