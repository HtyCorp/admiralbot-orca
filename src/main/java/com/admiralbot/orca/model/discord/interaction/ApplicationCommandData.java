package com.admiralbot.orca.model.discord.interaction;

import com.admiralbot.orca.model.discord.Snowflake;
import com.fasterxml.jackson.annotation.JsonProperty;

public record ApplicationCommandData(
        @JsonProperty(value = "id", required = true) Snowflake id,
        @JsonProperty(value = "name", required = true) String commandName,
        @JsonProperty(value = "type", required = true) ApplicationCommandType type,
        @JsonProperty("resolved")
        ) {}
