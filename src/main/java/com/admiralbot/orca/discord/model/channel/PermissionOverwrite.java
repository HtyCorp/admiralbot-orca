package com.admiralbot.orca.discord.model.channel;

import com.admiralbot.orca.discord.model.Snowflake;
import com.admiralbot.orca.discord.model.StringBitfield;
import com.admiralbot.orca.discord.model.guild.Permission;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PermissionOverwrite(
        @JsonProperty(value = "id", required = true) Snowflake id,
        // Super-simple effectively-enum componentType: 0=role, 1=member
        @JsonProperty(value = "type", required = true) Integer isMemberNotRole,
        @JsonProperty(value = "allow", required = true) StringBitfield<Permission> allow,
        @JsonProperty(value = "deny", required = true) StringBitfield<Permission> deny
) {}
