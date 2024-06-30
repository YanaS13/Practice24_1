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
        return parrot1;    }
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
    public Person person() {
        Person person = new Person();
        person.setName("Катя");
        person.setParrot1(parrot1());
        person.setParrot2(parrot2());
        person.setCat(cat());
        person.setDog(dog());
        return person;
    }
}
