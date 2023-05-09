package com.admiralbot.orca.discord.model.command;

import com.admiralbot.orca.discord.model.Locale;
import com.admiralbot.orca.discord.model.Snowflake;
import com.admiralbot.orca.discord.model.StringBitfield;
import com.admiralbot.orca.discord.model.guild.Permission;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ApplicationCommand(
        @JsonProperty(value = "id", required = true) Snowflake id,
        @JsonProperty("type") ApplicationCommandType type,
        @JsonProperty(value = "application_id", required = true) Snowflake applicationId,
        @JsonProperty("guild_id") Snowflake guildId,
        @JsonProperty(value = "name", required = true) String name,
        @JsonProperty("name_localizations") Map<Locale,String> nameLocalizations,
        @JsonProperty(value = "description", required = true) String description,
        @JsonProperty("description_localizations") Map<Locale,String> descriptionLocalizations,
        @JsonProperty("options") ApplicationCommandOption options,
        @JsonProperty("default_member_permissions") StringBitfield<Permission> defaultMemberPermissions,
        @JsonProperty("dm_permissions") Boolean dmPermission,
        @JsonProperty("nsfw") Boolean isNsfw,
        @JsonProperty(value = "version", required = true) Snowflake version
) {}
