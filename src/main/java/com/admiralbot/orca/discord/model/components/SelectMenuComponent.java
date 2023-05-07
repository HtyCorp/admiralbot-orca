package com.admiralbot.orca.discord.model.components;

import com.admiralbot.orca.discord.model.channel.ChannelType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
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
