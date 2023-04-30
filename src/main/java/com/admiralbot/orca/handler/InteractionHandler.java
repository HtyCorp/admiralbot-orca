package com.admiralbot.orca.handler;

import com.admiralbot.orca.auth.InteractionAuthenticator;
import com.admiralbot.orca.config.AppConfigClient;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;

import java.net.http.HttpClient;

public class InteractionHandler implements RequestHandler<APIGatewayV2HTTPEvent, APIGatewayV2HTTPResponse> {

    private static final String SIGNATURE_HEADER = "X-Signature-Ed25519";
    private static final String TIMESTAMP_HEADER = "X-Signature-Timestamp";

    private final InteractionAuthenticator interactionAuthenticator;

    // For Lambda init
    public InteractionHandler() {
        var httpClient = HttpClient.newHttpClient();
        var appConfigClient = new AppConfigClient(httpClient);
        this.interactionAuthenticator = new InteractionAuthenticator(appConfigClient);
    }

    // For unit testing
    public InteractionHandler(InteractionAuthenticator interactionAuthenticator) {
        this.interactionAuthenticator = interactionAuthenticator;
    }

    @Override
    public APIGatewayV2HTTPResponse handleRequest(APIGatewayV2HTTPEvent input, Context context) {

        // This validation would make more sense as an APIGW Lambda authorizer, but those cannot access request body.
        var signatureHex = input.getHeaders().get(SIGNATURE_HEADER);
        var timestamp = input.getHeaders().get(TIMESTAMP_HEADER);
        if (!interactionAuthenticator.authenticateTimestampedMessage(signatureHex, timestamp, input.getBody())) {
            return httpUnauthorizedResponse();
        }

        // TODO: Continue from here
    }

    private APIGatewayV2HTTPResponse httpUnauthorizedResponse() {
        // Ref: https://discord.com/developers/docs/interactions/receiving-and-responding#security-and-authorization
        // Discord doesn't seem to care what we put in here, as long as it's a 401.
        // E.g. I've omitted "WWW-Authenticate" header since there's no useful scheme/value to put there anyway.
        return APIGatewayV2HTTPResponse.builder()
                .withStatusCode(401)
                .withBody("Missing or invalid request signature")
                .build();
    }


}
