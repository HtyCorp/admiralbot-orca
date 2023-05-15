package com.admiralbot.orca.ops.commandsync;

import com.admiralbot.orca.discord.model.command.ApplicationCommandOption;
import com.admiralbot.orca.discord.model.command.ApplicationCommandOptionType;
import com.admiralbot.orca.discord.model.command.ApplicationCommandType;

import java.util.List;

public final class Commands {

    public static final List<ApplicationCommandParams> COMMANDS = List.of(
            ApplicationCommandParams.builder()
                    .name("hello")
                    .type(ApplicationCommandType.CHAT_INPUT)
                    .description("Test command that says hello back :)")
                    .options(List.of(
                            ApplicationCommandOption.builder()
                                    .required(false)
                                    .name("name")
                                    .type(ApplicationCommandOptionType.STRING)
                                    .description("Your name")
                                    .maxStringLength(128)
                                    .build()))
                    .build());

}
