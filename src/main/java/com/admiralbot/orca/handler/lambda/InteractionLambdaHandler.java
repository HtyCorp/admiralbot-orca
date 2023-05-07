package com.admiralbot.orca.handler.lambda;

import com.admiralbot.orca.auth.InteractionAuthenticator;
import com.admiralbot.orca.config.AppConfig;
import com.admiralbot.orca.handler.InteractionHandler;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;
import software.amazon.awssdk.http.urlconnection.UrlConnectionHttpClient;
import software.amazon.awssdk.services.appconfigdata.AppConfigDataClient;

@SuppressWarnings("unused")
public class InteractionLambdaHandler extends DelegateHandler<APIGatewayV2HTTPEvent, APIGatewayV2HTTPResponse> {

    @Override
    protected RequestHandler<APIGatewayV2HTTPEvent, APIGatewayV2HTTPResponse> createHandlerImpl() {
        var appConfigDataClient = AppConfigDataClient.builder()
                .httpClientBuilder(UrlConnectionHttpClient.builder())
                .build();
        var appConfig = new AppConfig(appConfigDataClient);
        var interactionAuthenticator = new InteractionAuthenticator(appConfig);
        return new InteractionHandler(interactionAuthenticator);
    }
}
