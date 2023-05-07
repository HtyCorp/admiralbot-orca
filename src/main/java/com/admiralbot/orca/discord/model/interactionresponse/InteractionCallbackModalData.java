package com.admiralbot.orca.discord.model.interactionresponse;

import com.admiralbot.orca.discord.model.components.MessageComponent;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

@Builder
public record InteractionCallbackModalData(
        @JsonProperty(value = "custom_id", required = true) String customId,
        @JsonProperty(value = "title", required = true) String title, // max 45 chars
        @JsonProperty(value = "components", required = true)List<MessageComponent> components // range: 1-5
) implements InteractionCallbackData {}
