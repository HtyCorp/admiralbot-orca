package com.admiralbot.orca.model.discord;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.math.BigInteger;
import java.util.Arrays;

public final class Bitfield<F extends Bitfield.Flag> {

    public interface Flag {
        int flagBit();
    }

    private final BigInteger bits;

    public Bitfield(BigInteger bits) {
        this.bits = bits;
    }

    @JsonCreator
    public Bitfield(String decimalString) {
        this(new BigInteger(decimalString));
    }

    @JsonValue
    public String asDecimalString() {
        return bits.toString();
    }

    @SafeVarargs // Array is read-only
    public static <T extends Flag> Bitfield<T> of(T... flags) {
        var resultBits = BigInteger.ZERO;
        for (var f: flags) {
            resultBits = resultBits.setBit(f.flagBit());
        }
        return new Bitfield<>(resultBits);
    }

    @SafeVarargs // Array is read-only
    public final boolean isSet(F... flags) {
        return Arrays.stream(flags).allMatch(f -> bits.testBit(f.flagBit()));
    }
}
