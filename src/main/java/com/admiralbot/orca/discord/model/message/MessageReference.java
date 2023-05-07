package com.admiralbot.orca.discord.model.message;

import com.admiralbot.orca.discord.model.Snowflake;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MessageReference(
        @JsonProperty("message_id") Snowflake originatingMessageId,
        @JsonProperty("channel_id") Snowflake originatingChannelId,
        @JsonProperty("guild_id") Snowflake originatingGuildId,
        @JsonProperty("fail_if_not_exists") Boolean failSendIfNotExists
) {}
