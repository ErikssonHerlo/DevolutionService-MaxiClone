package com.erikssonherlo.devolution.domain.model.enums;

import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

public enum Role {
    ADMINISTRATOR,
    CLIENT,
    EMPLOYEE;

    public static Role fromString(String role) {
        try {
            return Role.valueOf(role.toUpperCase()); // Maneja valores en minúscula o mayúscula
        } catch (IllegalArgumentException e) {
            throw new MethodArgumentTypeMismatchException(null, Role.class, role, null, e);
        }
    }
}
