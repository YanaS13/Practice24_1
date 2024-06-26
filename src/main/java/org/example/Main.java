package org.example;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Reflect reflect = new Reflect();
        DataContainer d = new DataContainer();
        System.out.println(reflect.reflection(18, 2, d));
    }
}