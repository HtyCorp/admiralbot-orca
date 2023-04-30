package com.admiralbot.orca.model.discord;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.math.BigInteger;

public class StringBitfield<F extends NumberBitfield.Flag> extends NumberBitfield<F> {

    @JsonCreator
    public StringBitfield(String decimalString) {
        super(new BigInteger(decimalString));
    }

    @JsonValue
    public String asString() {
        return bits.toString();
    }
}
