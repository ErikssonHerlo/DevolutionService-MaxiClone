package com.erikssonherlo.devolution.domain.model.enums;

import java.util.Arrays;

public enum DevolutionStatus {
    OPEN,
    ACCEPTED,
    REJECTED;

    public static String[] names() {
        return Arrays.stream(DevolutionStatus.values()).map(Enum::name).toArray(String[]::new);
    }

    public String toUpperCase() {
        return this.name().toUpperCase();
    }
}
