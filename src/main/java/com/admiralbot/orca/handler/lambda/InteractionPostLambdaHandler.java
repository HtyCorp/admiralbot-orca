package com.admiralbot.orca.handler.lambda;

import com.admiralbot.orca.auth.InteractionAuthenticator;
import com.admiralbot.orca.commands.CommandDispatcher;
import com.admiralbot.orca.handler.InteractionPostHandler;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
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

        preloadClasses();

        return new InteractionPostHandler(interactionAuthenticator, commandDispatcher);
    }

    private void preloadClasses() {

        var classNamesUrl = Objects.requireNonNull(getClass().getClassLoader().getResource("preloadClassNames.txt"));

        // Disable verbose class loading for the duration of this method, since we want to track "[class,load]" messages outside preload context to see if we
        // missed any classes in the list.
        var classLoaderSettings = ManagementFactory.getClassLoadingMXBean();
        var previousVerboseState = classLoaderSettings.isVerbose();
        classLoaderSettings.setVerbose(false);

        try (var lines = Files.lines(Paths.get(classNamesUrl.toURI()))) {
            lines.forEach(name -> {
                try {
                    System.out.println("About to try loading class: " + name);
                    Class.forName(name);
                } catch (Exception e) {
                    // This has the potential to create a lot of log pollution, but it would only be in version-publish streams, so it's limited.
                    e.printStackTrace();
                }
            });
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("Failed to read preload class names list", e);
        } finally {
            classLoaderSettings.setVerbose(previousVerboseState);
        }
    }
}
