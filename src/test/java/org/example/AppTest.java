package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    @Test
    void main() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Person p = context.getBean(Person.class);
        String wait =p.getDog().getName();
        String rider = "Настя";
        assertEquals(rider,wait);
    }
}