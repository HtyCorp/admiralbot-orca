package com.admiralbot.orca.model.discord.embed;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URL;

public record EmbedVideo(
        @JsonProperty("url") URL url,
        @JsonProperty("proxy_url") URL proxyUrl,
        @JsonProperty("height") Integer height,
        @JsonProperty("width") Integer width
) {}
