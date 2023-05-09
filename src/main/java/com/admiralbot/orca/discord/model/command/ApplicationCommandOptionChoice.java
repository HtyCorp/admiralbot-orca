package com.admiralbot.orca.discord.model.command;

import com.admiralbot.orca.discord.model.Locale;
import com.admiralbot.orca.discord.interaction.ApplicationCommandOptionValue;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.Map;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public record ApplicationCommandOptionChoice(
        @JsonProperty(value = "name", required = true) String name, // max 100 chars
        @JsonProperty("name_localizations") Map<Locale,String> nameLocalizations,
        @JsonProperty(value = "value", required = true) ApplicationCommandOptionValue value
) {}
