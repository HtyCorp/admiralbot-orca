package com.admiralbot.orca.discord.model.components;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MessageComponentType {

    ACTION_ROW(1),
    BUTTON(2),
    STRING_SELECT(3),
    TEXT_INPUT(4),
    USER_SELECT(5),
    ROLE_SELECT(6),
    MENTIONABLE_SELECT(7),
    CHANNEL_SELECT(8);

    @JsonValue
    @SuppressWarnings("unused")
    private final int apiValue;

    MessageComponentType(int apiValue) {
        this.apiValue = apiValue;
    }
}
