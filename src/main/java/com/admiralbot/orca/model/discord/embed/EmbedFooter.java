package com.admiralbot.orca.model.discord.embed;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URL;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EmbedFooter(
        @JsonProperty(value = "text", required = true) String text,
        @JsonProperty("icon_url") URL iconUrl,
        @JsonProperty("proxy_icon_url") URL proxyIconUrl
) {}
