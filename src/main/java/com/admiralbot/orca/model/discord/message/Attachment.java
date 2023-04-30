package com.admiralbot.orca.model.discord.message;

import com.admiralbot.orca.model.discord.Snowflake;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URL;

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
