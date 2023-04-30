package com.admiralbot.orca.model.discord;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.math.BigInteger;
import java.util.Arrays;

public record PermissionSet(@JsonValue String string) {

    @JsonCreator
    public PermissionSet {}

    public static PermissionSet of(Permission... permissions) {
        var permissionsInt = BigInteger.ZERO;
        for (var p: permissions) {
            permissionsInt = permissionsInt.setBit(p.flagBit());
        }
        return new PermissionSet(permissionsInt.toString());
    }

    public boolean hasPermission(Permission... permissions) {
        var permissionsInt = new BigInteger(string);
        return Arrays.stream(permissions).allMatch(p -> permissionsInt.testBit(p.flagBit()));
    }
}
