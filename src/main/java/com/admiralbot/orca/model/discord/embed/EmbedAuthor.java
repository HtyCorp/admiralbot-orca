package com.admiralbot.orca.model.discord.embed;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URL;

public record EmbedAuthor(
        @JsonProperty(value = "name", required = true) String name,
        @JsonProperty("url") URL url,
        @JsonProperty("icon_url") URL iconUrl,
        @JsonProperty("proxy_icon_url") URL proxyIconUrl
) {}