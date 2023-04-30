package com.admiralbot.orca.model.discord.guild;

import com.admiralbot.orca.model.discord.Bitfield;
import com.admiralbot.orca.model.discord.Snowflake;
import com.admiralbot.orca.model.discord.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.List;

public record Member(
        @JsonProperty("user") User user,
        @JsonProperty("nick") String nickname,
        @JsonProperty("avatar") String avatarHash,
        @JsonProperty(value = "roles", required = true) List<Snowflake> roleIds,
        @JsonProperty(value = "joined_at", required = true) Instant joinedAt,
        @JsonProperty("premium_since") Instant premiumSince,
        @JsonProperty(value = "deaf", required = true) Boolean isDeaf,
        @JsonProperty(value = "mute", required = true) Boolean isMute,
        @JsonProperty(value = "flag", required = true) Long flags, // No wrapper: unused
        @JsonProperty("pending") Boolean isPending,
        @JsonProperty("permissions") Bitfield<Permission> channelPermissions,
        @JsonProperty("communication_disabled_until") Instant communicationDisabledUntil
) {}