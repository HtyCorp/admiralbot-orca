package com.admiralbot.orca.discord.model.interaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ApplicationCommandInteractionDataOption(
        @JsonProperty(value = "name", required = true) String name,
        @JsonProperty(value = "type", required = true) ApplicationCommandOptionType type,
        // This can be any of 4 types, so it's tricky to deal with...
        @JsonProperty("value") ApplicationCommandOptionValue value,
        @JsonProperty("options") ApplicationCommandInteractionDataOption groupOrSubcommandOptions,
        @JsonProperty("focused") Boolean autocompleteFocused
) {}
