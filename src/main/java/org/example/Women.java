package org.example;

import lombok.NonNull;

public class Women extends People {
    @Override
    @AnnotationTime(hour = 18,priority = 1,description = "Данные девушки")
    public void person_data(@NonNull DataContainer dataContainer){
        dataContainer.setAge(21);
        dataContainer.setName("Аня");
        dataContainer.setGender("ж");
    }
    @Override
    @AnnotationTime(hour = 18,priority = 2,description = "info1")
    public void information(@NonNull DataContainer dataContainer){
        dataContainer.setAge(29);
        dataContainer.setName("Ксюша");
    }
}
