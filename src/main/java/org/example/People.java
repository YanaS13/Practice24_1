package org.example;

import lombok.NonNull;

@Days(weekdays = 2)
public abstract class People {
    public abstract void personalData(@NonNull DataContainer dataContainer);
    public abstract void informationAboutThePerson(@NonNull DataContainer dataContainer);

}

