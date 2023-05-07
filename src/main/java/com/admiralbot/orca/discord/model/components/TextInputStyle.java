package com.admiralbot.orca.discord.model.components;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TextInputStyle {

    SHORT(1),
    PARAGRAPH(2);

    @JsonValue
    @SuppressWarnings("unused")
    private final int apiValue;

    TextInputStyle(int apiValue) {
        this.apiValue = apiValue;
    }
}
