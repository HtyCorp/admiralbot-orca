package com.admiralbot.orca.model.discord.embed;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URL;
import java.time.Instant;
import java.util.List;

public record Embed(
        @JsonProperty("title") String title,
        // No enum: These are considered deprecated and likely to be removed in future API versions
        @JsonProperty("type") String type,
        @JsonProperty("description") String description,
        @JsonProperty("url") URL url,
        @JsonProperty("timestamp") Instant time,
        @JsonProperty("color") Integer color,
        @JsonProperty("footer") EmbedFooter footer,
        @JsonProperty("image") EmbedImage image,
        @JsonProperty("thumbnail") EmbedThumbnail thumbnail,
        @JsonProperty("video") EmbedVideo video,
        @JsonProperty("provider") EmbedProvider provider,
        @JsonProperty("author") EmbedAuthor author,
        @JsonProperty("fields") List<EmbedField> fields
) {}
