package com.admiralbot.orca.model.discord.embed;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URL;

public record EmbedThumbnail(
        @JsonProperty(value = "url", required = true) URL url,
        @JsonProperty("proxy_url") URL proxyUrl,
        @JsonProperty("height") Integer height,
        @JsonProperty("width") Integer width
) {}
