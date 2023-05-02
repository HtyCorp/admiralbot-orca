package com.admiralbot.orca.model.discord.interactionresponse;

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
