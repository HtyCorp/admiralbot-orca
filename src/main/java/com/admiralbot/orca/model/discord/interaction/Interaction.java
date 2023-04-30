package com.admiralbot.orca.model.discord.interaction;

import com.admiralbot.orca.model.discord.NumberBitfield;
import com.admiralbot.orca.model.discord.Snowflake;
import com.admiralbot.orca.model.discord.User;
import com.admiralbot.orca.model.discord.channel.Channel;
import com.admiralbot.orca.model.discord.guild.Member;
import com.admiralbot.orca.model.discord.guild.Permission;
import com.admiralbot.orca.model.discord.message.Message;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

public record Interaction(
   @JsonProperty(value = "id", required = true) Snowflake id,
   @JsonProperty(value = "application_id", required = true) Snowflake applicationId,
   @JsonProperty(value = "type", required = true) InteractionType type,

   /*
    * Uses existing `componentType` property above to determine subtype to parse data as.
    * This would probably blow up if serialized (string names, not int?), but we never serialize interactions so that's okay.
    */
   @JsonTypeInfo(use = Id.NAME, include = As.EXTERNAL_PROPERTY, property = "type", visible = true)
   @JsonSubTypes({
           // `PingData` is an empty record class since PING carries no data
           @Type(value = PingData.class, name = "1"),
           @Type(value = ApplicationCommandData.class, name = "2"),
           @Type(value = MessageComponentData.class, name = "3"),
           // APPLICATION_COMMAND_AUTOCOMPLETE uses the same interaction data as APPLICATION_COMMAND
           @Type(value = ApplicationCommandData.class, name = "4"),
           @Type(value = ModalSubmitData.class, name = "5")
   })
   @JsonProperty("data") // Not required: empty for PING interactions
   InteractionData data,

   @JsonProperty("guild_id") Snowflake guildId,
   @JsonProperty("channel") Channel channel,
   @JsonProperty("channel_id") Snowflake channelId,
   @JsonProperty("member") Member member,
   @JsonProperty("user") User user,
   @JsonProperty(value = "token", required = true) String token,
   @JsonProperty(value = "version", required = true) Integer version,
   @JsonProperty("message") Message message,
   @JsonProperty("app_permissions") NumberBitfield<Permission> appPermissions,
   @JsonProperty("locale") String locale,
   @JsonProperty("guild_locale") String guildLocale
) {}
