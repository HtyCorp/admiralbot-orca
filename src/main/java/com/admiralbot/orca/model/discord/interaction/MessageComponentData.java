package com.admiralbot.orca.model.discord.interaction;

import com.admiralbot.orca.model.discord.components.MessageComponentType;
import com.admiralbot.orca.model.discord.components.SelectOption;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record MessageComponentData(
        @JsonProperty(value = "custom_id", required = true) String customId,
        @JsonProperty(value = "component_type", required = true) MessageComponentType type,
        @JsonProperty("values") List<SelectOption> selectMenuValues
) implements InteractionData {}