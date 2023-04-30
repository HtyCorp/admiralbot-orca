package com.admiralbot.orca.model.discord.interaction;

import com.admiralbot.orca.model.discord.components.MessageComponent;
import com.fasterxml.jackson.annotation.JsonProperty;

public record ModalSubmitData(
        @JsonProperty(value = "custom_id", required = true) String customId,
        @JsonProperty(value = "components", required = true) MessageComponent components
) implements InteractionData {}
