
package org.component;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    @org.junit.jupiter.api.Test
    public void main() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Person p = context.getBean(Person.class);
        p.getDog().setName("Дерик");
        String wait = p.getDog().getName();
        String rider = "Дерик";
        assertEquals(rider, wait);
    }
}