package com.admiralbot.orca.model.discord.components;

import com.admiralbot.orca.model.discord.channel.ChannelType;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record SelectMenuComponent(
        @JsonProperty(value = "type", required = true) MessageComponentType type,
        @JsonProperty(value = "custom_id", required = true) String customId,
        @JsonProperty("options") List<SelectOption> options,
        @JsonProperty("channel_types") List<ChannelType> channelSelectTypes,
        @JsonProperty("placeholder") String placeholderText,
        @JsonProperty("min_values") Integer minSelections,
        @JsonProperty("max_values") Integer maxSelections,
        @JsonProperty("disabled") Boolean isDisabled
) implements MessageComponent {}
