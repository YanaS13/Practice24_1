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

    @Test
    public void maritalStatus() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        PersonalInformation personalInformation = context.getBean(PersonalInformation.class);
        LinkedList<String> nameOfChild = new LinkedList<>();
        nameOfChild.add("khihg");
        nameOfChild.add("jhg,igks");
        NullPointerException exception = assertThrows(NullPointerException.class, () ->
                personalInformation.maritalStatus(true, 2, nameOfChild));
        System.out.println(exception.getMessage());
    }
}