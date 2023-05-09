package com.admiralbot.orca.discord.model.command;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ApplicationCommandOptionType {

    SUB_COMMAND(1),
    SUB_COMMAND_GROUP(2),
    STRING(3),
    INTEGER(4),
    BOOLEAN(5),
    USER(6),
    CHANNEL(7),
    ROLE(8),
    MENTIONABLE(9),
    NUMBER(10),
    ATTACHMENT(11);

    @JsonValue
    @SuppressWarnings("unused")
    private final int apiValue;

    ApplicationCommandOptionType(int apiValue) {
        this.apiValue = apiValue;
    }
}
