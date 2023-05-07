package com.admiralbot.orca.discord.model.channel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ThreadMetadata(
        @JsonProperty(value = "archived", required = true) boolean isArchived,
        @JsonProperty(value = "auto_archive_duration", required = true) Integer autoArchiveDurationMinutes,
        @JsonProperty(value = "archive_timestamp", required = true) Instant archiveTime,
        @JsonProperty(value = "locked", required = true) boolean isLocked,
        @JsonProperty("invitable") Boolean isPrivateThreadInvitable,
        @JsonProperty("create_timestamp") Instant createTime
) {}
