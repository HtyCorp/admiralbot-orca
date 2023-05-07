package com.admiralbot.orca.discord.model.components;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ButtonStyle {

    PRIMARY(1),
    SECONDARY(2),
    SUCCESS(3),
    DANGER(4),
    LINK(5);

    @JsonValue
    @SuppressWarnings("unused")
    private final int apiValue;

    ButtonStyle(int apiValue) {
        this.apiValue = apiValue;
    }
}
