package org.example;

import lombok.NonNull;

@Days(weekdays = 2)
public class Men extends People {
    @Override
    @AnnotationTime(hour = 10, priority = 1, description = "Данные о мужчине")
    public void personalData(@NonNull DataContainer dataContainer) {
        dataContainer.setAge(35);
        dataContainer.setName("Вася");
        dataContainer.setGender("м");
    }

    @Override
    @AnnotationTime(hour = 8, priority = 2, description = "Данные мужчины")
    public void informationAboutThePerson(@NonNull DataContainer dataContainer) {
        dataContainer.setAge(60);
        dataContainer.setName("Паша");
    }
}