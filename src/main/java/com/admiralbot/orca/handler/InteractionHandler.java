package com.admiralbot.orca.handler;

import com.admiralbot.orca.auth.InteractionAuthenticator;
import com.admiralbot.orca.model.discord.interaction.Interaction;
import com.admiralbot.orca.model.discord.interaction.InteractionType;
import com.admiralbot.orca.model.discord.interactionresponse.InteractionCallbackType;
import com.admiralbot.orca.model.discord.interactionresponse.InteractionResponse;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.log4j.Log4j2;

@Log4j2 // TODO: Maybe SLF4J?
public class InteractionHandler implements RequestHandler<APIGatewayV2HTTPEvent, APIGatewayV2HTTPResponse> {

    private static final String SIGNATURE_HEADER = "X-Signature-Ed25519";
    private static final String TIMESTAMP_HEADER = "X-Signature-Timestamp";

    private static final ObjectMapper JSON = new ObjectMapper();
    static {
        JSON.registerModule(new JavaTimeModule());
        JSON.disable(DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY);
    }

    private final InteractionAuthenticator interactionAuthenticator;

    public InteractionHandler(InteractionAuthenticator interactionAuthenticator) {
        this.interactionAuthenticator = interactionAuthenticator;
    }

    @Override
    public APIGatewayV2HTTPResponse handleRequest(APIGatewayV2HTTPEvent input, Context context) {

        // This validation would make more sense as an APIGW Lambda authorizer, but those cannot access request body.
        var signatureHex = input.getHeaders().get(SIGNATURE_HEADER);
        var timestamp = input.getHeaders().get(TIMESTAMP_HEADER);
        if (!interactionAuthenticator.authenticateTimestampedMessage(signatureHex, timestamp, input.getBody())) {
            return httpUnauthorized();
        }

        if (input.getIsBase64Encoded()) {
            return httpBadRequest("Request is binary/encoded");
        }

        Interaction interaction;
        try {
            log.debug(() -> "Interaction JSON: \n" + input.getBody());
            interaction = JSON.readValue(input.getBody(), Interaction.class);
            log.debug(() -> "Interaction object: \n" + interaction);
        } catch (JsonProcessingException e) {
            // Throw an exception without trying to recover: if we can't parse this it means we have a bug
            throw new RuntimeException("Failed to parse valid/authorized Discord interaction data", e);
        }

        var interactionResponse = getInteractionResponse(interaction);
        String interactionResponseJson;
        try {
            log.debug(() -> "Interaction response object: \n" + interactionResponse);
            interactionResponseJson = JSON.writeValueAsString(interactionResponse);
            log.debug(() -> "Interaction response JSON: \n" + interactionResponseJson);
        } catch (JsonProcessingException e) {
            // Throw instead of catching for same reason as above
            throw new RuntimeException("Failed to write interaction response string", e);
        }

        return httpOkay(interactionResponseJson);
    }

    private InteractionResponse getInteractionResponse(Interaction interaction) {

        if (interaction.type() == InteractionType.PING) {
            return InteractionResponse.builder()
                    .type(InteractionCallbackType.PONG)
                    .build();
        }

        throw new IllegalArgumentException("Non-PING payloads not implemented yet");
    }

    private APIGatewayV2HTTPResponse httpOkay(String msg) {
        return APIGatewayV2HTTPResponse.builder()
                .withStatusCode(200)
                .withBody(msg)
                .build();
    }

    private APIGatewayV2HTTPResponse httpUnauthorized() {
        // Ref: https://discord.com/developers/docs/interactions/receiving-and-responding#security-and-authorization
        // Discord doesn't seem to care what we put in here, as long as it's a 401.
        // E.g. I've omitted "WWW-Authenticate" header since there's no useful scheme/value to put there anyway.
        return APIGatewayV2HTTPResponse.builder()
                .withStatusCode(401)
                .withBody("Missing or invalid request signature")
                .build();
    }

    private APIGatewayV2HTTPResponse httpBadRequest(String msg) {
        return APIGatewayV2HTTPResponse.builder()
                .withStatusCode(400)
                .withBody(msg)
                .build();
    }


}
