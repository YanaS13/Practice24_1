package org.example;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class ReflectTest {

    @org.junit.jupiter.api.Test
    void reflection() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Reflect reflect = new Reflect();
        DataContainer d = new DataContainer();
        String wait = reflect.reflection(18, 2, d);
        String rider = "DataContainer(name=Аня, gender=ж, age=21)\n" +
                "DataContainer(name=Ксюша, gender=ж, age=29)\n";
        assertEquals(rider,wait);
    }
}