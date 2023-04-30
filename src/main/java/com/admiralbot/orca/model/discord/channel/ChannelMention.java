package com.admiralbot.orca.model.discord.channel;

import com.admiralbot.orca.model.discord.Snowflake;
import com.fasterxml.jackson.annotation.JsonProperty;

public record ChannelMention(
        @JsonProperty(value = "id", required = true) Snowflake id,
        @JsonProperty(value = "guild_id", required = true) Snowflake guildId,
        @JsonProperty(value = "type", required = true) ChannelType type,
        @JsonProperty(value = "name", required = true) String name
) {}
