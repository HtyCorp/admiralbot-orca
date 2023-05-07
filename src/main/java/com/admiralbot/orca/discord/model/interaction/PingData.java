package com.admiralbot.orca.discord.model.interaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Contains no data. This is just a placeholder to make parsing PING interactions possible for {@link Interaction}.
 *
 * <p>The {@link com.fasterxml.jackson.annotation.JsonSubTypes} annotation on the {@link InteractionData} member of {@link Interaction}
 * needs a valid subtype for all possible <code>componentType</code> property values, including PING, even though PING does not carry data.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PingData() implements InteractionData {}
