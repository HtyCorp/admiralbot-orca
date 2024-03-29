package com.admiralbot.orca.discord.interaction;

import com.admiralbot.orca.discord.model.components.MessageComponentType;
import com.admiralbot.orca.discord.model.components.SelectOption;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MessageComponentData(
        @JsonProperty(value = "custom_id", required = true) String customId,
        @JsonProperty(value = "component_type", required = true) MessageComponentType componentType,
        @JsonProperty("values") List<SelectOption> selectMenuValues
) implements InteractionData {}
