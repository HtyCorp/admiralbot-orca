package com.admiralbot.orca.discord.model.embed;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URL;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EmbedVideo(
        @JsonProperty("url") URL url,
        @JsonProperty("proxy_url") URL proxyUrl,
        @JsonProperty("height") Integer height,
        @JsonProperty("width") Integer width
) {}
