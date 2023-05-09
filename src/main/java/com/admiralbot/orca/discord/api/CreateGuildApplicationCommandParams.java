package com.admiralbot.orca.discord.api;

import com.admiralbot.orca.discord.model.Locale;
import com.admiralbot.orca.discord.model.StringBitfield;
import com.admiralbot.orca.discord.model.command.ApplicationCommandOption;
import com.admiralbot.orca.discord.model.command.ApplicationCommandType;
import com.admiralbot.orca.discord.model.guild.Permission;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;
import java.util.Map;

@Builder
public record CreateGuildApplicationCommandParams(
        @JsonProperty(value = "name", required = true) String name,
        @JsonProperty("name_localizations") Map<Locale,String> nameLocalizations,
        @JsonProperty("description") String description,
        @JsonProperty("description_localizations") Map<Locale,String> descriptionLocalizations,
        @JsonProperty("options") List<ApplicationCommandOption> options,
        @JsonProperty("default_member_permissions") StringBitfield<Permission> defaultMemberPermissions,
        @JsonProperty("type") ApplicationCommandType type,
        @JsonProperty("nsfw") Boolean isNsfw
) {

    public static String getPath(String applicationId, String guildId) {
        return String.format("/applications/%s/guilds/%s/commands", applicationId, guildId);
    }
}
