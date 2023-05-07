package com.admiralbot.orca.discord.model.channel;

import com.admiralbot.orca.discord.model.Snowflake;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ChannelMention(
        @JsonProperty(value = "id", required = true) Snowflake id,
        @JsonProperty(value = "guild_id", required = true) Snowflake guildId,
        @JsonProperty(value = "type", required = true) ChannelType type,
        @JsonProperty(value = "name", required = true) String name
) {}
