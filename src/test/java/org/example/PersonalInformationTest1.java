package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PersonalInformationTest1 {

    @Test
    public void passportData() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        PersonalInformation personalInformation = context.getBean(PersonalInformation.class);
        NullPointerException exception = assertThrows(NullPointerException.class, () ->
                personalInformation.passportData(null, null, null));
        System.out.println(exception.getMessage());
    }



}