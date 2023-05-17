package com.admiralbot.orca.commands;

import com.admiralbot.orca.discord.interaction.ApplicationCommandData;
import com.admiralbot.orca.discord.interaction.Interaction;
import com.admiralbot.orca.discord.interactionresponse.InteractionResponse;

public interface CommandHandler {

    InteractionResponse handleCommand(Interaction interaction, ApplicationCommandData data);

}
