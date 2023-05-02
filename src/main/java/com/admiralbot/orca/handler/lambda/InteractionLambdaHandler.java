package com.admiralbot.orca.handler.lambda;

import com.admiralbot.orca.auth.InteractionAuthenticator;
import com.admiralbot.orca.config.AppConfigClient;
import com.admiralbot.orca.handler.InteractionHandler;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;

import java.net.http.HttpClient;

@SuppressWarnings("unused")
public class InteractionLambdaHandler extends DelegateHandler<APIGatewayV2HTTPEvent, APIGatewayV2HTTPResponse> {

    @Override
    protected RequestHandler<APIGatewayV2HTTPEvent, APIGatewayV2HTTPResponse> createHandlerImpl() {
        var httpClient = HttpClient.newHttpClient();
        var appConfigClient = new AppConfigClient(httpClient);
        var interactionAuthenticator = new InteractionAuthenticator(appConfigClient);
        return new InteractionHandler(interactionAuthenticator);
    }
}
