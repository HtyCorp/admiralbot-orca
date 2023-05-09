package com.admiralbot.orca.handler.lambda;

import com.admiralbot.orca.auth.InteractionAuthenticator;
import com.admiralbot.orca.handler.InteractionPostHandler;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Log4j2
@SuppressWarnings("unused")
public class InteractionPostLambdaHandler extends DelegateHandler<APIGatewayV2HTTPEvent, APIGatewayV2HTTPResponse> {

    @Override
    protected RequestHandler<APIGatewayV2HTTPEvent, APIGatewayV2HTTPResponse> createHandlerImpl() {
        var discordAppKeySet = Optional.ofNullable(System.getenv("DISCORD_APP_KEY_SET"))
                .map(ks -> List.of(ks.split(",")))
                .orElseThrow();
        var interactionAuthenticator = new InteractionAuthenticator(discordAppKeySet);
        var handler = new InteractionPostHandler(interactionAuthenticator);

        // Run 'warmup' requests as part of SnapStart optimal init
        warmUpPingRequest(handler);

        return handler;
    }

    private void warmUpPingRequest(InteractionPostHandler handler) {
        // This is a captured/valid signature copied exactly as-is (not editable because any change would invalidate signature)
        var requestBody = "{\"application_id\":\"1101898412019957770\",\"id\":\"1104777861904212059\"," +
                "\"token\":\"aW50ZXJhY3Rpb246MTEwNDc3Nzg2MTkwNDIxMjA1OTpveThZVE85Mk1CSW1yYXRDOFNvYVVqTmtwQVR2andUc1BvQT" +
                "dZYllKNmJwREVkdVdXYWFpYXY1SlIwRDVScDdtT2hpZTg5N05xemhEY3EyaHRXUkhpdlBHUXU4aEpxbFZyNnRDNGFidzFjMFVnVkZB" +
                "UlJWUWE2dHFoT3Y0b1ZHVQ\",\"type\":1,\"user\":{\"avatar\":\"46ec846ec006c096229a4f2bb7e597a1\"," +
                "\"avatar_decoration\":null,\"discriminator\":\"7674\",\"display_name\":null,\"global_name\":null," +
                "\"id\":\"307387893029142535\",\"public_flags\":4194304,\"username\":\"Mamish\"},\"version\":1}";
        var request = APIGatewayV2HTTPEvent.builder()
                .withVersion("2.0")
                .withRawPath("/")
                .withHeaders(Map.of(
                        "content-length", "522",
                        "x-signature-ed25519", "0d4702da582a865ea1a7ab3263befe298c7806a3f84cf4c212a82e6281c91a22" +
                                "0540e65034ae903fb1be2f243937c8247767c30094fe99d0b705dde292a69c06",
                        "x-signature-timestamp", "1683469968",
                        "user-agent", "Discord-Interactions/1.0 (+https://discord.com)"
                ))
                .withBody(requestBody)
                .build();

        var response = handler.handleRequest(request, null);
        log.debug("Warmup response:\n{}", response);
    }
}
