package com.erikssonherlo.common.application.anotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Represents an Input Port in the Hexagonal Architecture.
 * Used to identify the interfaces that define the contract
 * for interacting with the application's use cases or business logic.
 *
 * @author erikssonherlo
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InputPort {

    /**
     * The value may indicate a suggestion for a logical component name,
     * which could be useful if you're registering input port beans in the context.
     * @return the suggested component name, if any (or empty String otherwise)
     */
    String value() default "";
}