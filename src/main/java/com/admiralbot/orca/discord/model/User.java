package com.admiralbot.orca.discord.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record User(
        @JsonProperty(value = "id", required = true) Snowflake id,
        @JsonProperty(value = "username", required = true) String username,
        @JsonProperty(value = "discriminator", required = true) String discriminator,
        @JsonProperty("avatar") String avatarHash,
        @JsonProperty("bot") Boolean isBot,
        @JsonProperty("system") Boolean isSystem,
        @JsonProperty("mfa_enabled") Boolean isMfaEnabled,
        @JsonProperty("banner") String bannerHash,
        @JsonProperty("accent_color") Integer accentColor,
        @JsonProperty("locale") String locale,
        @JsonProperty("verified") Boolean isVerified,
        @JsonProperty("email") String email,
        @JsonProperty("flags") Long flags, // No wrapper componentType since we have no known use for this info
        @JsonProperty("premium_type")  Integer nitroType, // Likewise ^
        @JsonProperty("public_flags") Long publicFlags // Same as `flags`, but non-private only I guess?
) {}
