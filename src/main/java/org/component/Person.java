package org.component;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data
public class Person {
    private String name;
    @Autowired
    private Parrot parrot1;
    @Autowired
    private Parrot parrot2;
    @Autowired
    private Cat cat;
    @Autowired
    private Dog dog;
}