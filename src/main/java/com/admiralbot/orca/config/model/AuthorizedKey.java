package com.admiralbot.orca.config.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthorizedKey(
        @JsonProperty("publicKeyHex") String publicKeyHex
) {}
