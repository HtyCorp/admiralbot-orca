package com.admiralbot.orca.model.discord.interaction;

import com.admiralbot.orca.model.discord.Bitfield;
import com.admiralbot.orca.model.discord.Snowflake;
import com.admiralbot.orca.model.discord.User;
import com.admiralbot.orca.model.discord.channel.Channel;
import com.admiralbot.orca.model.discord.guild.Member;
import com.admiralbot.orca.model.discord.guild.Permission;
import com.admiralbot.orca.model.discord.message.Message;
import com.fasterxml.jackson.annotation.JsonProperty;

public record Interaction(
   @JsonProperty(value = "id", required = true) Snowflake id,
   @JsonProperty(value = "application_id", required = true) Snowflake applicationId,
   @JsonProperty(value = "type", required = true) InteractionType type,
   @JsonProperty("data") InteractionData data,
   @JsonProperty("guild_id") Snowflake guildId,
   @JsonProperty("channel") Channel channel,
   @JsonProperty("channel_id") Snowflake channelId,
   @JsonProperty("member") Member member,
   @JsonProperty("user") User user,
   @JsonProperty(value = "token", required = true) String token,
   @JsonProperty(value = "version", required = true) Integer version,
   @JsonProperty("message") Message message,
   @JsonProperty("app_permissions") Bitfield<Permission> appPermissions,
   @JsonProperty("locale") String locale,
   @JsonProperty("guild_locale") String guildLocale
) {}
