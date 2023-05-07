package com.admiralbot.orca.discord.model.components;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ActionRowComponent(
        @JsonProperty(value = "type", required = true) MessageComponentType type,
        @JsonProperty(value = "components", required = true) List<MessageComponent> components
) implements MessageComponent {}
