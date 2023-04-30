package com.admiralbot.orca.model.discord;

/**
 * Bitwise flags indicating Discord permissions.
 * <a href="https://discord.com/developers/docs/topics/permissions#permissions-bitwise-permission-flags">Reference</a>
 */
enum Permission {

    // Generated programmatically from above table HTML using Python ElementTree.
    //
    // You'll have to reconstruct this when needed, but essential code snippet is:
    // ```
    // for tr in tdata: # Where tdata is the relevant tdata HTML element in above docs
    //   print("    /**\n" +
    //        f"     * {tr[2].text}.\n" +
    //        f"     * Channel type restriction: {tr[3].text}\n" +
    //         "     */\n" +
    //        f"    {list(e)[0].text.split(' ')[0]}({re.search(r'(?<=<< )[0-9]+', tr[1][1].text).group()}),\n")
    // ```

    /**
     * Allows creation of instant invites.
     * Channel type restriction: T, V, S
     */
    CREATE_INSTANT_INVITE(0),

    /**
     * Allows kicking members.
     * Channel type restriction: None
     */
    KICK_MEMBERS(1),

    /**
     * Allows banning members.
     * Channel type restriction: None
     */
    BAN_MEMBERS(2),

    /**
     * Allows all permissions and bypasses channel permission overwrites.
     * Channel type restriction: None
     */
    ADMINISTRATOR(3),

    /**
     * Allows management and editing of channels.
     * Channel type restriction: T, V, S
     */
    MANAGE_CHANNELS(4),

    /**
     * Allows management and editing of the guild.
     * Channel type restriction: None
     */
    MANAGE_GUILD(5),

    /**
     * Allows for the addition of reactions to messages.
     * Channel type restriction: T, V, S
     */
    ADD_REACTIONS(6),

    /**
     * Allows for viewing of audit logs.
     * Channel type restriction: None
     */
    VIEW_AUDIT_LOG(7),

    /**
     * Allows for using priority speaker in a voice channel.
     * Channel type restriction: V
     */
    PRIORITY_SPEAKER(8),

    /**
     * Allows the user to go live.
     * Channel type restriction: V, S
     */
    STREAM(9),

    /**
     * Allows guild members to view a channel, which includes reading messages in text channels and joining voice channels.
     * Channel type restriction: T, V, S
     */
    VIEW_CHANNEL(10),

    /**
     * Allows for sending messages in a channel and creating threads in a forum (does not allow sending messages in threads).
     * Channel type restriction: T, V, S
     */
    SEND_MESSAGES(11),

    /**
     * Allows for sending of .
     * Channel type restriction: T, V, S
     */
    SEND_TTS_MESSAGES(12),

    /**
     * Allows for deletion of other users messages.
     * Channel type restriction: T, V, S
     */
    MANAGE_MESSAGES(13),

    /**
     * Links sent by users with this permission will be auto-embedded.
     * Channel type restriction: T, V, S
     */
    EMBED_LINKS(14),

    /**
     * Allows for uploading images and files.
     * Channel type restriction: T, V, S
     */
    ATTACH_FILES(15),

    /**
     * Allows for reading of message history.
     * Channel type restriction: T, V, S
     */
    READ_MESSAGE_HISTORY(16),

    /**
     * Allows for using the .
     * Channel type restriction: T, V, S
     */
    MENTION_EVERYONE(17),

    /**
     * Allows the usage of custom emojis from other servers.
     * Channel type restriction: T, V, S
     */
    USE_EXTERNAL_EMOJIS(18),

    /**
     * Allows for viewing guild insights.
     * Channel type restriction: None
     */
    VIEW_GUILD_INSIGHTS(19),

    /**
     * Allows for joining of a voice channel.
     * Channel type restriction: V, S
     */
    CONNECT(20),

    /**
     * Allows for speaking in a voice channel.
     * Channel type restriction: V
     */
    SPEAK(21),

    /**
     * Allows for muting members in a voice channel.
     * Channel type restriction: V, S
     */
    MUTE_MEMBERS(22),

    /**
     * Allows for deafening of members in a voice channel.
     * Channel type restriction: V
     */
    DEAFEN_MEMBERS(23),

    /**
     * Allows for moving of members between voice channels.
     * Channel type restriction: V, S
     */
    MOVE_MEMBERS(24),

    /**
     * Allows for using voice-activity-detection in a voice channel.
     * Channel type restriction: V
     */
    USE_VAD(25),

    /**
     * Allows for modification of own nickname.
     * Channel type restriction: None
     */
    CHANGE_NICKNAME(26),

    /**
     * Allows for modification of other users nicknames.
     * Channel type restriction: None
     */
    MANAGE_NICKNAMES(27),

    /**
     * Allows management and editing of roles.
     * Channel type restriction: T, V, S
     */
    MANAGE_ROLES(28),

    /**
     * Allows management and editing of webhooks.
     * Channel type restriction: T, V, S
     */
    MANAGE_WEBHOOKS(29),

    /**
     * Allows management and editing of emojis, stickers, and soundboard sounds.
     * Channel type restriction: None
     */
    MANAGE_GUILD_EXPRESSIONS(30),

    /**
     * Allows members to use application commands, including slash commands and context menu commands..
     * Channel type restriction: T, V, S
     */
    USE_APPLICATION_COMMANDS(31),

    /**
     * Allows for requesting to speak in stage channels. (.
     * Channel type restriction: S
     */
    REQUEST_TO_SPEAK(32),

    /**
     * Allows for creating, editing, and deleting scheduled events.
     * Channel type restriction: V, S
     */
    MANAGE_EVENTS(33),

    /**
     * Allows for deleting and archiving threads, and viewing all private threads.
     * Channel type restriction: T
     */
    MANAGE_THREADS(34),

    /**
     * Allows for creating public and announcement threads.
     * Channel type restriction: T
     */
    CREATE_PUBLIC_THREADS(35),

    /**
     * Allows for creating private threads.
     * Channel type restriction: T
     */
    CREATE_PRIVATE_THREADS(36),

    /**
     * Allows the usage of custom stickers from other servers.
     * Channel type restriction: T, V, S
     */
    USE_EXTERNAL_STICKERS(37),

    /**
     * Allows for sending messages in threads.
     * Channel type restriction: T
     */
    SEND_MESSAGES_IN_THREADS(38),

    /**
     * Allows for using Activities (applications with the .
     * Channel type restriction: V
     */
    USE_EMBEDDED_ACTIVITIES(39),

    /**
     * Allows for timing out users to prevent them from sending or reacting to messages in chat and threads, and from speaking in voice and stage channels.
     * Channel type restriction: None
     */
    MODERATE_MEMBERS(40),

    /**
     * Allows for viewing role subscription insights.
     * Channel type restriction: None
     */
    VIEW_CREATOR_MONETIZATION_ANALYTICS(41),

    /**
     * Allows for using soundboard in a voice channel.
     * Channel type restriction: V
     */
    USE_SOUNDBOARD(42),

    /**
     * Allows sending voice messages.
     * Channel type restriction: T, V, S
     */
    SEND_VOICE_MESSAGES(46);

    private final int flagBit;

    Permission(int flagBit) {
        this.flagBit = flagBit;
    }

    public int flagBit() {
        return flagBit;
    }
}
