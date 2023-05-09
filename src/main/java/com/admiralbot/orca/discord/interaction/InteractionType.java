package com.admiralbot.orca.discord.interaction;

import com.fasterxml.jackson.annotation.JsonValue;

public enum InteractionType {

    PING(1),
    APPLICATION_COMMAND(2),
    MESSAGE_COMPONENT(3),
    APPLICATION_COMMAND_AUTOCOMPLETE(4),
    MODAL_SUBMIT(5);

    @JsonValue
    @SuppressWarnings("unused")
    private final int apiValue;

    InteractionType(int apiValue) {
        this.apiValue = apiValue;
    }
}
