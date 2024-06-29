package org.example;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import static org.junit.Assert.assertEquals;

class ReflectTest {

    @org.junit.jupiter.api.Test
    void reflection() throws InvocationTargetException, InstantiationException, IllegalAccessException, FileNotFoundException, UnsupportedEncodingException {
        Reflect reflect = new Reflect();
        DataContainer dataContainer = new DataContainer();
        String wait = reflect.reflection(18, 2, dataContainer);
        String rider = "DataContainer(name=Аня, gender=ж, age=21)\n" +
                "DataContainer(name=Ксюша, gender=ж, age=29)\n";
        assertEquals(rider,wait);
    }
}