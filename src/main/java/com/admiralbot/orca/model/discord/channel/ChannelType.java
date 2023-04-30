package com.admiralbot.orca.model.discord.channel;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ChannelType {

    GUILD_TEXT(0),
    DM(1),
    GUILD_VOICE(2),
    GROUP_DM(3),
    GUILD_CATEGORY(4),
    GUILD_ANNOUNCEMENT(5),
    ANNOUNCEMENT_THREAD(6),
    PUBLIC_THREAD(10),
    PRIVATE_THREAD(12),
    GUILD_STAGE_VOICE(13),
    GUILD_DIRECTORY(14),
    GUILD_FORUM(15);

    @JsonValue
    @SuppressWarnings("unused")
    private final int apiValue;

    ChannelType(int apiValue) {
        this.apiValue = apiValue;
    }
}
