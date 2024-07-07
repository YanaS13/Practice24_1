package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public Parrot parrot1() {
        Parrot parrot1 = new Parrot();
        parrot1.setName("Ара");
        return parrot1;
    }

    @Bean
    public Parrot parrot2() {
        Parrot parrot1 = new Parrot();
        parrot1.setName("Жора");
        return parrot1;
    }

    @Bean
    public Cat cat() {
        Cat cat = new Cat();
        cat.setName("Барсик");
        return cat;
    }

    @Bean
    public Dog dog() {
        Dog dog = new Dog();
        dog.setName("Настя");
        return dog;
    }

    @Bean
    public Person person(Parrot parrot1, Parrot parrot2, Cat cat, Dog dog) {
        Person person = new Person(parrot1, parrot2, cat, dog);
        return person;
    }
}
