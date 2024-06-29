package org.example;
import java.lang.annotation.*;
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Days {
    int weekdays();
}