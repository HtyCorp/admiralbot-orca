package com.admiralbot.orca.model.discord.interaction;

import com.admiralbot.orca.model.discord.Member;
import com.admiralbot.orca.model.discord.PermissionSet;
import com.admiralbot.orca.model.discord.Snowflake;
import com.admiralbot.orca.model.discord.User;
import com.admiralbot.orca.model.discord.channel.Channel;
import com.fasterxml.jackson.annotation.JsonProperty;

public record Interaction(
   @JsonProperty(value = "id", required = true) Snowflake id,
   @JsonProperty(value = "application_id", required = true) Snowflake applicationId,
   @JsonProperty(value = "type", required = true) InteractionType type,
    // TODO: interaction data
   @JsonProperty("guild_id") Snowflake guildId,
   @JsonProperty("channel") Channel channel,
   @JsonProperty("channel_id") Snowflake channelId,
   @JsonProperty("member") Member member,
   @JsonProperty("user") User user,
   @JsonProperty(value = "token", required = true) String token,
   @JsonProperty(value = "version", required = true) Integer version,
   // TODO: message
   @JsonProperty("app_permissions") PermissionSet appPermissions,
   @JsonProperty("locale") String locale,
   @JsonProperty("guild_locale") String guildLocale
) {}
