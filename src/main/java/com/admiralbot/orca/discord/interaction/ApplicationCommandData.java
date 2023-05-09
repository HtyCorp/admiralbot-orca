package com.admiralbot.orca.discord.interaction;

import com.admiralbot.orca.discord.model.Snowflake;
import com.admiralbot.orca.discord.model.command.ApplicationCommandType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ApplicationCommandData(
        @JsonProperty(value = "id", required = true) Snowflake id,
        @JsonProperty(value = "name", required = true) String commandName,
        @JsonProperty(value = "type", required = true) ApplicationCommandType type,
        @JsonProperty("resolved") ResolvedData resolvedData,
        @JsonProperty("options") List<ApplicationCommandInteractionDataOption> options,
        @JsonProperty("guild_id") Snowflake guildId,
        @JsonProperty("target_id") Snowflake userOrMessageTargetId
) implements InteractionData {}
