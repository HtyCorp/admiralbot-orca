package com.admiralbot.orca.model.discord.channel;

import com.admiralbot.orca.model.discord.Bitfield;
import com.admiralbot.orca.model.discord.Snowflake;
import com.admiralbot.orca.model.discord.guild.Permission;
import com.fasterxml.jackson.annotation.JsonProperty;

public record PermissionOverwrite(
        @JsonProperty(value = "id", required = true) Snowflake id,
        // Super-simple effectively-enum type: 0=role, 1=member
        @JsonProperty(value = "type", required = true) Integer isMemberNotRole,
        @JsonProperty(value = "allow", required = true) Bitfield<Permission> allow,
        @JsonProperty(value = "deny", required = true) Bitfield<Permission> deny
) {}
