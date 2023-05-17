package com.admiralbot.orca.commands;

import com.admiralbot.orca.discord.interaction.ApplicationCommandData;
import com.admiralbot.orca.discord.interaction.Interaction;
import com.admiralbot.orca.discord.interactionresponse.AllowedMentions;
import com.admiralbot.orca.discord.interactionresponse.InteractionCallbackMessageData;
import com.admiralbot.orca.discord.interactionresponse.InteractionCallbackType;
import com.admiralbot.orca.discord.interactionresponse.InteractionResponse;

import java.util.List;

public class HelloCommandHandler implements CommandHandler {

    @Override
    public InteractionResponse handleCommand(Interaction interaction, ApplicationCommandData data) {
        final String content;
        if (data.options().isEmpty()) {
            content = "Hello to you too!";
        } else {
            // This isn't very typesafe. We'll figure that part out later.
            var name = data.options().get(0).value().asString();
            content = "Hello, " + name + "!";
        }
        return InteractionResponse.builder()
                .type(InteractionCallbackType.CHANNEL_MESSAGE_WITH_SOURCE)
                .data(InteractionCallbackMessageData.builder()
                        .content(content)
                        .allowedMentions(AllowedMentions.builder()
                                .parse(List.of())
                                .build())
                        .build())
                .build();
    }
}
