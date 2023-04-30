package com.admiralbot.orca.model.discord;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.math.BigInteger;
import java.util.Arrays;

public class NumberBitfield<F extends NumberBitfield.Flag> {

    public interface Flag {
        int flagBit();
    }

    @JsonValue
    protected final BigInteger bits;

    @JsonCreator
    public NumberBitfield(BigInteger bits) {
        this.bits = bits;
    }

    @SafeVarargs // Array is read-only
    public static <T extends Flag> NumberBitfield<T> of(T... flags) {
        var resultBits = BigInteger.ZERO;
        for (var f: flags) {
            resultBits = resultBits.setBit(f.flagBit());
        }
        return new NumberBitfield<>(resultBits);
    }

    @SafeVarargs // Array is read-only
    public final boolean isSet(F... flags) {
        return Arrays.stream(flags).allMatch(f -> bits.testBit(f.flagBit()));
    }
}
