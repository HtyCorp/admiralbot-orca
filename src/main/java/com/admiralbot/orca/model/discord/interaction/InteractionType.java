package com.admiralbot.orca.model.discord.interaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonIgnoreProperties(ignoreUnknown = true)
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
