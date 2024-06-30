package org.example;

import lombok.Data;

@Data
public class Person {
    private String name;
    private Parrot parrot1;
    private Parrot parrot2;
    private Cat cat;
    private Dog dog;

}
