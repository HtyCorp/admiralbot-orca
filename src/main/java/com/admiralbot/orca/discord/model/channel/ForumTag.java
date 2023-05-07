package com.admiralbot.orca.discord.model.channel;

import com.admiralbot.orca.discord.model.Snowflake;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ForumTag(
        @JsonProperty(value = "id", required = true) Snowflake id,
        @JsonProperty(value = "name", required = true) String name,
        @JsonProperty(value = "moderated", required = true) boolean isModerated,
        @JsonProperty("emoji_id") Snowflake emojiId,
        @JsonProperty("emoji_name") String emojiName
) {}
