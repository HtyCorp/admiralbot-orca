package com.admiralbot.orca.discord.model.interactionresponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record InteractionResponse(
        @JsonProperty(value = "type", required = true) InteractionCallbackType type,
        @JsonProperty("data") InteractionCallbackData data // optional, since PING/PONG doesn't require it
) {}
