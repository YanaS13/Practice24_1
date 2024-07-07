package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppTest {

    @Test
    public void main() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Person person = context.getBean(Person.class);
        person.getDog().getName();
        person.getCat().getName();
    }

}
