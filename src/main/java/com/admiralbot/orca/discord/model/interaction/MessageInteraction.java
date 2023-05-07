package com.admiralbot.orca.discord.model.interaction;

import com.admiralbot.orca.discord.model.Snowflake;
import com.admiralbot.orca.discord.model.User;
import com.admiralbot.orca.discord.model.guild.Member;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MessageInteraction(
        @JsonProperty(value = "id", required = true) Snowflake id,
        @JsonProperty(value = "type", required = true) InteractionType type,
        @JsonProperty(value = "name", required = true) String applicationCommandName,
        @JsonProperty(value = "user", required = true) User invokingUser,
        @JsonProperty("member") Member invokingMember
) {}
