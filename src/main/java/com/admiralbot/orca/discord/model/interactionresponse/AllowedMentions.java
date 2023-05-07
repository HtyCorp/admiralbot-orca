package com.admiralbot.orca.discord.model.interactionresponse;

import com.admiralbot.orca.discord.model.Snowflake;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

@Builder
public record AllowedMentions(
        @JsonProperty(value = "parse", required = true) List<AllowedMentionType> parse,
        @JsonProperty(value = "roles", required = true) List<Snowflake> roleIds,
        @JsonProperty(value = "users", required = true) List<Snowflake> userIds,
        @JsonProperty(value = "replied_user", required = true) boolean mentionUserRepliedTo
) {}