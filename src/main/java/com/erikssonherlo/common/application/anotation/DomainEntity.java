package com.erikssonherlo.common.application.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Represents a domain entity on the Hexagonal Architecture
 * Used to identify the classes that are part of the Domain layer
 * and are responsible for the business logic of the application
 * (e.g. entities, value objects, etc.)
 * @author erikssonherlo
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DomainEntity {
}