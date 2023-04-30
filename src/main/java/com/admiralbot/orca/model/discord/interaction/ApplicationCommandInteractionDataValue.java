package com.admiralbot.orca.model.discord.interaction;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Optional;

public class ApplicationCommandInteractionDataValue {

    private String stringVal;
    private Integer integerVal;
    private Double doubleVal;
    private Boolean booleanVal;

    @JsonCreator
    private ApplicationCommandInteractionDataValue(Object value) {
        var vClass = value.getClass();
        if (value instanceof String s) {
            stringVal = s;
        } else if (value instanceof Integer i) {
            integerVal = i;
        } else if (value instanceof Double d) {
            doubleVal = d;
        } else if (value instanceof Boolean b) {
            booleanVal = b;
        } else {
            throw new IllegalArgumentException("Unexpected componentType '" + vClass + "' for value '" + value + "'");
        }
    }

    @JsonValue
    public Object jsonValue() {
        if (stringVal != null) {
            return stringVal;
        } else if (integerVal != null) {
            return integerVal;
        } else if (doubleVal != null) {
            return doubleVal;
        } else if (booleanVal != null) {
            return booleanVal;
        } else {
            throw new IllegalStateException("Null value componentType");
        }
    }

    public ApplicationCommandInteractionDataValue(String stringVal) {
        this((Object)stringVal);
    }

    public ApplicationCommandInteractionDataValue(Integer integerVal) {
        this((Object)integerVal);
    }

    public ApplicationCommandInteractionDataValue(Double doubleVal) {
        this((Object)doubleVal);
    }

    public ApplicationCommandInteractionDataValue(Boolean booleanVal) {
        this((Object)booleanVal);
    }

    public String getStringVal() {
        return Optional.ofNullable(stringVal).orElseThrow();
    }

    public Integer getIntegerVal() {
        return Optional.ofNullable(integerVal).orElseThrow();
    }

    public Double getDoubleVal() {
        return Optional.ofNullable(doubleVal).orElseThrow();
    }

    public Boolean getBooleanVal() {
        return Optional.ofNullable(booleanVal).orElseThrow();
    }
}
