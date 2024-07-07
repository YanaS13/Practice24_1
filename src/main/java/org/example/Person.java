package org.example;

import lombok.Data;

@Data
public class Person {
    private String name;
    private Parrot parrot1;
    private Parrot parrot2;
    private Cat cat;
    private Dog dog;

    public Person(Parrot parrot1, Parrot parrot2, Cat cat, Dog dog) {
        this.parrot1 = parrot1;
        this.parrot2 = parrot2;
        this.cat = cat;
        this.dog = dog;
    }
}
