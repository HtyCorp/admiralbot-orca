package com.admiralbot.orca.handler.lambda;

import com.admiralbot.orca.auth.InteractionAuthenticator;
import com.admiralbot.orca.commands.CommandDispatcher;
import com.admiralbot.orca.handler.InteractionPostHandler;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Optional;

@Log4j2
@SuppressWarnings("unused")
public class InteractionPostLambdaHandler extends DelegateHandler<APIGatewayV2HTTPEvent, APIGatewayV2HTTPResponse> {

    @Override
    protected RequestHandler<APIGatewayV2HTTPEvent, APIGatewayV2HTTPResponse> createHandlerImpl() {
        var discordAppKeySet = Optional.ofNullable(System.getenv("DISCORD_APP_KEY_SET"))
                .map(ks -> List.of(ks.split(",")))
                .orElseThrow();
        var interactionAuthenticator = new InteractionAuthenticator(true, discordAppKeySet);
        var commandDispatcher = new CommandDispatcher();

        // TODO: Perform class pre-loading here

        return new InteractionPostHandler(interactionAuthenticator, commandDispatcher);
    }
}
