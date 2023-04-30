package com.admiralbot.orca.model.discord.interaction;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ApplicationCommandType {

    CHAT_INPUT(1),
    USER(2),
    MESSAGE(3);

    @JsonValue
    @SuppressWarnings("unused")
    private final int apiValue;

    ApplicationCommandType(int apiValue) {
        this.apiValue = apiValue;
    }
}
