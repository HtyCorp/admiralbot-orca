package com.admiralbot.orca.model.discord;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PermissionOverwrite(
        @JsonProperty(value = "id", required = true) Snowflake id,
        // Super-simple effectively-enum type: 0=role, 1=member
        @JsonProperty(value = "type", required = true) Integer isMemberNotRole,
        @JsonProperty(value = "allow", required = true) PermissionSet allow,
        @JsonProperty(value = "deny", required = true) PermissionSet deny
) {}
