package org.example;

import lombok.NonNull;

@Days(weekdays = 2)
public abstract class People {
    public abstract void person_data(DataContainer dataContainer);
    public abstract void information(DataContainer dataContainer);

}

