package com.admiralbot.orca.model.discord.interaction;

import com.admiralbot.orca.model.discord.Snowflake;
import com.admiralbot.orca.model.discord.User;
import com.admiralbot.orca.model.discord.channel.Channel;
import com.admiralbot.orca.model.discord.guild.Member;
import com.admiralbot.orca.model.discord.guild.Role;
import com.admiralbot.orca.model.discord.message.Attachment;
import com.admiralbot.orca.model.discord.message.Message;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public record ResolvedData(
        @JsonProperty("users") Map<Snowflake, User> users,
        @JsonProperty("members") Map<Snowflake, Member> members,
        @JsonProperty("roles") Map<Snowflake, Role> roles,
        @JsonProperty("channels") Map<Snowflake, Channel> channels,
        @JsonProperty("messages") Map<Snowflake, Message> messages,
        @JsonProperty("attachments") Map<Snowflake, Attachment> attachments
) {}
