package com.admiralbot.orca.model.discord.components;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ActionRowComponent(
        @JsonProperty(value = "type", required = true) MessageComponentType type,
        @JsonProperty(value = "components", required = true) List<MessageComponent> components
) implements MessageComponent {}
