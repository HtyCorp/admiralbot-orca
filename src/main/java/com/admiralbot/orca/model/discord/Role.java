package com.admiralbot.orca.model.discord;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Role(
        @JsonProperty(value = "id", required = true) Snowflake id,
        @JsonProperty(value = "name", required = true) String name,
        @JsonProperty(value = "color", required = true) Integer color,
        @JsonProperty(value = "hoist", required = true) boolean isHoisted,
        @JsonProperty("icon") String iconHash,
        @JsonProperty("unicode_emoji") String emojiUnicodeChar,
        @JsonProperty(value = "position", required = true) Integer position,
        @JsonProperty(value = "managed", required = true) boolean isManaged,
        @JsonProperty(value = "mentionable", required = true) boolean isMentionable
        // ignoring "tags" since it's weirdly serialized and not useful right now
) {}
