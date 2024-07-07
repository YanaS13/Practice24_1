package org.example;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonalInformation {
    @NotEmpty
    public String passportData(String name, Integer age, String address) {
        return "Данные записаны";
    }

    @NotEmpty
    public void maritalStatus(Boolean married, Integer numberOfChild, List<String> nameOfChild) {

    }
}
