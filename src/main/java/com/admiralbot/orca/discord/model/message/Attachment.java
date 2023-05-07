package com.admiralbot.orca.discord.model.message;

import com.admiralbot.orca.discord.model.Snowflake;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URL;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Attachment(
        @JsonProperty(value = "id", required = true) Snowflake id,
        @JsonProperty("filename") String filename,
        @JsonProperty("description") String description,
        @JsonProperty("content_type") String contentMimeType,
        @JsonProperty("size") Long sizeBytes,
        @JsonProperty("url") URL sourceUrl,
        @JsonProperty("proxy_url") URL proxyUrl,
        @JsonProperty("height") Integer imageHeight,
        @JsonProperty("width") Integer imageWidth,
        @JsonProperty("ephemeral") Boolean isEphemeral,
        @JsonProperty("duration_secs") Double audioDurationSeconds,
        @JsonProperty("waveform") String audioWaveformBase64
) {}
