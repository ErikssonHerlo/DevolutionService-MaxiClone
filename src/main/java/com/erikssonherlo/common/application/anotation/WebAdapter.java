package com.erikssonherlo.common.application.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Represents a Web adapter on the Hexagonal Architecture
 * Used to identify the classes that are part of the Infrastructure layer
 * and are responsible for the communication between the application and the external world
 * (e.g. REST controllers, SOAP web services, etc.)
 *
 * @author erikssonherlo
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface WebAdapter {
}