package com.erikssonherlo.common.application.anotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

/**
 * Represents a Persistence adapter on the Hexagonal Architecture
 * Used to identify the classes that are part of the Infrastructure layer
 * and are responsible for the communication between the application and the database
 * (e.g. repositories, DAOs, etc.)
 *
 * @author erikssonherlo
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Component
public @interface PersistenceAdapter {

    @AliasFor(annotation = Component.class)
    String value() default "";
}