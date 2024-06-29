package org.example;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Reflect {
    public List<Class> initialization(List<Class> listOfClassHeirs) {
        listOfClassHeirs.add(Children.class);
        listOfClassHeirs.add(Men.class);
        listOfClassHeirs.add(Women.class);
        return listOfClassHeirs;
    }

    private String createAndInvoke(List<Class> listOfHeirs, int dayOfTheWeek, int hours, DataContainer dataContainer) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        List<Method> listOfMetods = new ArrayList<>();
        String str = "";
        Object instanceOfTheclass = null;
        listOfHeirs = listOfHeirs.stream().filter(x -> ((Days) x.getAnnotation(Days.class)).weekdays() == dayOfTheWeek).collect(Collectors.toList());
        for (Class element : listOfHeirs) {
            instanceOfTheclass = element.newInstance();
            listOfMetods = List.of(element.getDeclaredMethods());
        }
        listOfMetods = listOfMetods.stream().filter(x -> ((AnnotationTime) x.getAnnotation(AnnotationTime.class)).hour() == hours).collect(Collectors.toList());
        listOfMetods = listOfMetods.stream().sorted(Comparator.comparingInt(x -> ((AnnotationTime) x.getAnnotation(AnnotationTime.class)).priority())).toList();
        for (Method element : listOfMetods) {
            element.invoke(instanceOfTheclass, dataContainer);
            str = str + dataContainer + '\n';
        }
        return str;
    }

    private void createAndInvoke2(List<Class> listOfHeirs, int dayOfTheWeek, int hours, DataContainer dataContainer) throws InstantiationException, IllegalAccessException, InvocationTargetException, FileNotFoundException, UnsupportedEncodingException {
        WorkWithFiles file = new WorkWithFiles();
        file.creatLog();
        List<Method> listOfMetods = new ArrayList<>();
        Object instanceOfTheclass = null;
        listOfHeirs = listOfHeirs.stream().filter(x -> ((Days) x.getAnnotation(Days.class)).weekdays() == dayOfTheWeek).collect(Collectors.toList());
        for (Class element : listOfHeirs) {
            instanceOfTheclass = element.newInstance();
            listOfMetods = List.of(element.getDeclaredMethods());
        }
        listOfMetods = listOfMetods.stream().filter(x -> ((AnnotationTime) x.getAnnotation(AnnotationTime.class)).hour() == hours).collect(Collectors.toList());
        listOfMetods = listOfMetods.stream().sorted(Comparator.comparingInt(x -> ((AnnotationTime) x.getAnnotation(AnnotationTime.class)).priority())).toList();
        for (Method element : listOfMetods) {
            element.invoke(instanceOfTheclass, dataContainer);
            file.writingToAFile(dataContainer.toString());
        }
    }

    public String reflection(int hour, int data, DataContainer dataContainer) throws InvocationTargetException, InstantiationException, IllegalAccessException, FileNotFoundException, UnsupportedEncodingException {
        List<Class> listOfPersons = new ArrayList<>();
        listOfPersons = initialization(listOfPersons);
        String str = createAndInvoke(listOfPersons, data, hour, dataContainer);
        createAndInvoke2(listOfPersons, data, hour, dataContainer);
        return str;
    }
}
