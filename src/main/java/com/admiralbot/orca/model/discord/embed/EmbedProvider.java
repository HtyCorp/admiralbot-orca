package com.admiralbot.orca.model.discord.embed;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URL;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EmbedProvider(
        @JsonProperty("name") String name,
        @JsonProperty("url") URL url
) {}
