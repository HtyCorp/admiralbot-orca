package com.admiralbot.orca.model.discord.channel;

import com.admiralbot.orca.model.discord.guild.Member;
import com.admiralbot.orca.model.discord.Snowflake;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record ThreadMember(
        @JsonProperty("id") Snowflake threadId,
        @JsonProperty("user_id") Snowflake userId,
        @JsonProperty(value = "join_timestamp", required = true) Instant joinTime,
        @JsonProperty(value = "flags", required = true) Long flags, // Exact flag meaning is undocumented
        @JsonProperty("member") Member member
) {}
