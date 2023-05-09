package com.admiralbot.orca.discord.interaction;

import com.admiralbot.orca.discord.model.Snowflake;
import com.admiralbot.orca.discord.model.User;
import com.admiralbot.orca.discord.model.channel.Channel;
import com.admiralbot.orca.discord.model.guild.Member;
import com.admiralbot.orca.discord.model.guild.Role;
import com.admiralbot.orca.discord.model.message.Attachment;
import com.admiralbot.orca.discord.model.message.Message;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ResolvedData(
        @JsonProperty("users") Map<Snowflake, User> users,
        @JsonProperty("members") Map<Snowflake, Member> members,
        @JsonProperty("roles") Map<Snowflake, Role> roles,
        @JsonProperty("channels") Map<Snowflake, Channel> channels,
        @JsonProperty("messages") Map<Snowflake, Message> messages,
        @JsonProperty("attachments") Map<Snowflake, Attachment> attachments
) {}
