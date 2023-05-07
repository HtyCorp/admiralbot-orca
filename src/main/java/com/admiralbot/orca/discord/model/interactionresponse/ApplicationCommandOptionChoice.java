package com.admiralbot.orca.discord.model.interactionresponse;

import com.admiralbot.orca.discord.model.Locale;
import com.admiralbot.orca.discord.model.interaction.ApplicationCommandOptionValue;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.Map;

@Builder
public record ApplicationCommandOptionChoice(
        @JsonProperty(value = "name", required = true) String name, // max 100 chars
        @JsonProperty("name_localization") Map<Locale,String> nameLocalizations,
        @JsonProperty(value = "value", required = true) ApplicationCommandOptionValue value
) {}
