package com.admiralbot.orca.discord.model.guild;

import com.admiralbot.orca.discord.model.Snowflake;
import com.admiralbot.orca.discord.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Emoji(
        @JsonProperty("id") Snowflake id, // Unclear why this is optional
        @JsonProperty("name") String name,
        @JsonProperty("roles") List<Snowflake> allowedRoleIds,
        @JsonProperty("user") User creator,
        @JsonProperty("requires_colons") Boolean mustBeColonWrapped,
        @JsonProperty("managed") Boolean isManaged,
        @JsonProperty("animated") Boolean isAnimated,
        @JsonProperty("available") Boolean isAvailable
) {}
