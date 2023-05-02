package com.admiralbot.orca.model.discord.interactionresponse;

import com.admiralbot.orca.model.discord.Snowflake;
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