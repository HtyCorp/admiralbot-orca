package com.admiralbot.orca.discord.model.interactionresponse;

import com.fasterxml.jackson.annotation.JsonValue;

public enum InteractionCallbackType {

    PONG(1),
    CHANNEL_MESSAGE_WITH_SOURCE(4),
    DEFERRED_CHANNEL_MESSAGE_WITH_SOURCE(5),
    DEFERRED_UPDATE_MESSAGE(6),
    UPDATE_MESSAGE(7),
    APPLICATION_COMMAND_AUTOCOMPLETE_RESULT(8),
    MODAL(9);

    @JsonValue
    @SuppressWarnings("unused")
    private final int apiValue;

    InteractionCallbackType(int apiValue) {
        this.apiValue = apiValue;
    }
}
