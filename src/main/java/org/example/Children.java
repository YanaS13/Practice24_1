package org.example;

import lombok.NonNull;

@Days(weekdays = 5)
public abstract class Children extends People {
    @Override
    @AnnotationTime(hour = 3,priority = 2,description = "Данные ребенка")
    public void person_data(@NonNull DataContainer dataContainer){
        dataContainer.setAge(5);
        dataContainer.setName("Мила");
        dataContainer.setGender("ж");
    }
    @Override
    @AnnotationTime(hour = 6,priority = 1,description = "info2")
    public void information(@NonNull DataContainer dataContainer){
        dataContainer.setAge(8);
        dataContainer.setName("Коля");
        dataContainer.setGender("м");
    }

}
