package com.admiralbot.orca.discord.model.message;

import com.admiralbot.orca.discord.model.NumberBitfield;

public enum MessageFlag implements NumberBitfield.Flag {

    CROSSPOSTED(0),
    IS_CROSSPOST(1),
    SUPPRESS_EMBEDS(2),
    SOURCE_MESSAGE_DELETED(3),
    URGENT(4),
    HAS_THREAD(5),
    EPHEMERAL(6),
    LOADING(7),
    FAILED_TO_MENTION_SOME_ROLES_IN_THREAD(8),
    SUPPRESS_NOTIFICATIONS(12),
    IS_VOICE_MESSAGE(13);

    private final int flagBit;

    MessageFlag(int flagBit) {
        this.flagBit = flagBit;
    }

    @Override
    public int flagBit() {
        return flagBit;
    }
}
