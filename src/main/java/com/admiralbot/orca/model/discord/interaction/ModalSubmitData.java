package com.admiralbot.orca.model.discord.interaction;

import com.admiralbot.orca.model.discord.components.MessageComponent;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ModalSubmitData(
        @JsonProperty(value = "custom_id", required = true) String customId,
        @JsonProperty(value = "components", required = true) MessageComponent components
) implements InteractionData {}
