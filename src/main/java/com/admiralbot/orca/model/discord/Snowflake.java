package com.admiralbot.orca.model.discord;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;

// Adds a little more componentType information than just "String" for IDs, by marking it as a Discord snowflake ID.
// This can be interpreted as a `long` later on if that becomes useful.
@JsonIgnoreProperties(ignoreUnknown = true)
public record Snowflake(@JsonValue String string) {
    @JsonCreator
    public Snowflake {}
}
