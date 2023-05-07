package com.admiralbot.orca.discord.model.interactionresponse;

import com.fasterxml.jackson.annotation.JsonValue;

public enum AllowedMentionType {

    ROLES("roles"),
    USERS("users"),
    EVERYONE("everyone");

    @JsonValue
    @SuppressWarnings("unused")
    private final String apiValue;

    AllowedMentionType(String apiValue) {
        this.apiValue = apiValue;
    }
}
