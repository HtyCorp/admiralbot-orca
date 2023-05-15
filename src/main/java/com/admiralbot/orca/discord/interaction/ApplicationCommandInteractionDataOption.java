package com.admiralbot.orca.discord.interaction;

import com.admiralbot.orca.discord.model.command.ApplicationCommandOptionType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ApplicationCommandInteractionDataOption(
        @JsonProperty(value = "name", required = true) String name,
        @JsonProperty(value = "type", required = true) ApplicationCommandOptionType type,
        // This can be any of 4 types, so it's tricky to deal with...
        @JsonProperty("value") ApplicationCommandOptionValue value,
        @JsonProperty("options") ApplicationCommandInteractionDataOption subOptions,
        @JsonProperty("focused") Boolean autocompleteFocused
) {}
