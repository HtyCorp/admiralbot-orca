package com.admiralbot.orca.model.discord.embed;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URL;

public record EmbedProvider(
        @JsonProperty("name") String name,
        @JsonProperty("url") URL url
) {}
