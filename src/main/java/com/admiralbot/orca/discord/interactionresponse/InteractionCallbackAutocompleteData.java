package com.admiralbot.orca.discord.interactionresponse;

import com.admiralbot.orca.discord.model.command.ApplicationCommandOptionChoice;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record InteractionCallbackAutocompleteData(
        @JsonProperty("choices") ApplicationCommandOptionChoice choices
) implements InteractionCallbackData {}
