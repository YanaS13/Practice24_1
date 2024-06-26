package org.example;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Repeatable(AnnotationTimes.class)
    public @interface AnnotationTime {
    int hour();
    int priority();
    String description();
}
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface AnnotationTimes {
    AnnotationTime[] value();
}
