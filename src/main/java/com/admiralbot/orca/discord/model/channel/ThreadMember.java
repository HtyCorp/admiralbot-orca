package com.admiralbot.orca.discord.model.channel;

import com.admiralbot.orca.discord.model.Snowflake;
import com.admiralbot.orca.discord.model.guild.Member;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ThreadMember(
        @JsonProperty("id") Snowflake threadId,
        @JsonProperty("user_id") Snowflake userId,
        @JsonProperty(value = "join_timestamp", required = true) Instant joinTime,
        @JsonProperty(value = "flags", required = true) Long flags, // Exact flag meaning is undocumented
        @JsonProperty("member") Member member
) {}
