package com.admiralbot.orca.model.discord.channel;

import com.admiralbot.orca.model.discord.Snowflake;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DefaultReaction(
        @JsonProperty(value = "emoji_id", required = true) Snowflake emojiId,
        @JsonProperty(value = "emoji_name", required = true) String emojiUnicodeChar
) { }
