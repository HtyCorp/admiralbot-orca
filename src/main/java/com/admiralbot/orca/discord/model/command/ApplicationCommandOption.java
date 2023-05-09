package com.admiralbot.orca.discord.model.command;

import com.admiralbot.orca.discord.model.Locale;
import com.admiralbot.orca.discord.model.channel.ChannelType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;
import java.util.Map;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public record ApplicationCommandOption(
        @JsonProperty(value = "type", required = true) ApplicationCommandOptionType type,
        @JsonProperty(value = "name", required = true) String name,
        @JsonProperty("name_localizations") Map<Locale,String> nameLocalizations,
        @JsonProperty(value = "description", required = true) String description,
        @JsonProperty(value = "description_localizations", required = true) Map<Locale,String> descriptionLocalizations,
        @JsonProperty("required") boolean required,
        @JsonProperty("choices") List<ApplicationCommandOptionChoice> choices,
        @JsonProperty("options") List<ApplicationCommandOption> subChoices,
        @JsonProperty("channel_types") List<ChannelType> channelTypes,
        // Min/max is supposed to be int for INTEGER types, so might cause errors depends on how serialization works.
        // May need to tweak Jackson to force it to serialize integer doubles as non-floating-point.
        @JsonProperty("min_value") Double minNumberValue,
        @JsonProperty("max_value") Double maxNumberValue,
        @JsonProperty("min_length") Integer minStringLength,
        @JsonProperty("max_length") Integer maxStringLength,
        @JsonProperty("autocomplete") Boolean autocompleteEnabled
) {}
