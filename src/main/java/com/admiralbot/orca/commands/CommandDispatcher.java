package com.admiralbot.orca.commands;

import com.admiralbot.orca.discord.interaction.ApplicationCommandData;
import com.admiralbot.orca.discord.interaction.Interaction;
import com.admiralbot.orca.discord.interactionresponse.AllowedMentions;
import com.admiralbot.orca.discord.interactionresponse.InteractionCallbackMessageData;
import com.admiralbot.orca.discord.interactionresponse.InteractionCallbackType;
import com.admiralbot.orca.discord.interactionresponse.InteractionResponse;
import com.admiralbot.orca.discord.model.NumberBitfield;
import com.admiralbot.orca.discord.model.message.MessageFlag;
import lombok.extern.log4j.Log4j2;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.admiralbot.orca.metrics.EmbeddedMetrics.*;

@Log4j2
public class CommandDispatcher {

    private final Map<String,CommandHandler> commandHandlers = new HashMap<>();

    public CommandDispatcher() {
        commandHandlers.put("hello", new HelloCommandHandler());
    }

    public InteractionResponse dispatch(Interaction interaction, ApplicationCommandData data) {

        var metricCommandFault = false;
        var metricCommandNotFound = false;
        var startInstant = Instant.now();

        try {

            var handler = commandHandlers.get(data.commandName());
            if (handler == null) {
                metricCommandNotFound = true;
                return noSuchCommandResponse(data.commandName());
            }

            try {
                return handler.handleCommand(interaction, data);
            } catch (RuntimeException e) {
                log.error("Unexpected error in handler for '{}'", data.commandName(), e);
                return runtimeExceptionResponse();
            }

        } finally {
            var dimensions = Map.of("CommandName", data.commandName());
            millisMetric("CommandLatency", dimensions, startInstant);
            booleanMetric("CommandNotFound", dimensions, metricCommandNotFound);
            booleanMetric("CommandFault", dimensions, metricCommandFault);
        }
    }

    private InteractionResponse noSuchCommandResponse(String commandName) {
        // This *shouldn't* happen since the application defines the commands, not the users. They can't call arbitrary commands.
        var messageContent = "Something has gone wrong! This server doesn't understand the command '" + commandName + "'";
        return ephemeralMessageResponse(messageContent);
    }

    private InteractionResponse runtimeExceptionResponse() {
        var messageContent = "Something went wrong! This error has been logged for review. Maybe try again later?";
        return ephemeralMessageResponse(messageContent);
    }

    private InteractionResponse ephemeralMessageResponse(String messageContent) {

        return InteractionResponse.builder()
                .type(InteractionCallbackType.CHANNEL_MESSAGE_WITH_SOURCE)
                .data(InteractionCallbackMessageData.builder()
                        .flags(NumberBitfield.of(MessageFlag.EPHEMERAL))
                        .allowedMentions(AllowedMentions.builder()
                                .parse(List.of())
                                .build())
                        .content(messageContent)
                        .build())
                .build();
    }
}
