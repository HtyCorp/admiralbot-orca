package com.admiralbot.orca.model.discord.interaction;

import java.util.Optional;

public class ApplicationCommandInteractionDataValue {

    private String stringVal;
    private Integer integerVal;
    private Double doubleVal;
    private Boolean booleanVal;

    public ApplicationCommandInteractionDataValue(String stringVal) {
        this.stringVal = stringVal;
    }

    public ApplicationCommandInteractionDataValue(Integer integerVal) {
        this.integerVal = integerVal;
    }

    public ApplicationCommandInteractionDataValue(Double doubleVal) {
        this.doubleVal = doubleVal;
    }

    public ApplicationCommandInteractionDataValue(Boolean booleanVal) {
        this.booleanVal = booleanVal;
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
