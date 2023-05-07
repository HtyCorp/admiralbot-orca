package com.admiralbot.orca.discord.model.interactionresponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record InteractionCallbackAutocompleteData(
        @JsonProperty("choices") ApplicationCommandOptionChoice choices
) implements InteractionCallbackData {}
