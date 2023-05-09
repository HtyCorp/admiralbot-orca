package com.admiralbot.orca.discord.interaction;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

public class ApplicationCommandOptionValue {

    @JsonValue
    private final Object value;

    @JsonCreator // I am doubtful this will work (needs type-specific constructors if not)
    public ApplicationCommandOptionValue(Object value) {
        this.value = value;
    }

    public static ApplicationCommandOptionValue ofString(String value) {
        Objects.requireNonNull(value);
        return new ApplicationCommandOptionValue(value);
    }

    public static ApplicationCommandOptionValue ofInt(int value) {
        return new ApplicationCommandOptionValue(value);
    }

    public static ApplicationCommandOptionValue ofDouble(double value) {
        return new ApplicationCommandOptionValue(value);
    }

    public static ApplicationCommandOptionValue ofBoolean(boolean value) {
        return new ApplicationCommandOptionValue(value);
    }

    public String asString() {
        return (String) value;
    }

    public int asInt() {
        return (int) value;
    }

    public double asDouble() {
        return (double) value;
    }

    public boolean asBoolean() {
        return (boolean) value;
    }
}
