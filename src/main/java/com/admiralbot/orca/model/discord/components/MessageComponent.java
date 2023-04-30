package com.admiralbot.orca.model.discord.components;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;

/**
 * Marker interface for message components, which are all unique subtypes
 */
//@JsonDeserialize(using = MessageComponentDeserializer.class)
//@JsonTypeIdResolver(MessageComponentTypeIdResolver.class)
@JsonTypeInfo(use = Id.NAME, include = As.EXISTING_PROPERTY, property = "type", visible = true)
@JsonSubTypes({
        @Type(value = ActionRowComponent.class, name = "1"),
        @Type(value = ButtonComponent.class, name = "2"),
        @Type(value = SelectMenuComponent.class, names = {"3","5","6","7","8"}),
        @Type(value = TextInputComponent.class, name = "4")
})
public interface MessageComponent {}
