package com.admiralbot.orca.model.discord;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Message(
        @JsonProperty(value = "id", required = true) Snowflake id,
        // TODO
) {}
