package com.admiralbot.orca.model.discord.embed;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URL;

public record EmbedFooter(
        @JsonProperty(value = "text", required = true) String text,
        @JsonProperty("icon_url") URL iconUrl,
        @JsonProperty("proxy_icon_url") URL proxyIconUrl
) {}
