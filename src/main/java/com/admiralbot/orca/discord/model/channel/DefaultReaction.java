package com.admiralbot.orca.discord.model.channel;

import com.admiralbot.orca.discord.model.Snowflake;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DefaultReaction(
        @JsonProperty(value = "emoji_id", required = true) Snowflake emojiId,
        @JsonProperty(value = "emoji_name", required = true) String emojiUnicodeChar
) { }
