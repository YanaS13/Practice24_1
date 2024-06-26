package org.example;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Reflect {
    public List<Class> initialization(List<Class> persons1) {
        persons1.add(Children.class);
        persons1.add(Men.class);
        persons1.add(Women.class);
        return persons1;
    }

    public String search(List<Class> listik, int nummy, int hours, DataContainer dataContainer) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        List<Method> listm = new ArrayList<>();
        String str = "";
        Object s = null;
        listik = listik.stream().filter(x -> ((Days) x.getAnnotation(Days.class)).weekdays() == nummy).collect(Collectors.toList());
        for (Class lis : listik) {
            s = lis.newInstance();
            listm = List.of(lis.getDeclaredMethods());
        }
        listm = listm.stream().filter(x -> ((AnnotationTime) x.getAnnotation(AnnotationTime.class)).hour() == hours).collect(Collectors.toList());
        listm = listm.stream().sorted(Comparator.comparingInt(x -> ((AnnotationTime) x.getAnnotation(AnnotationTime.class)).priority())).toList();
        for (Method lism : listm) {
            lism.invoke(s, dataContainer);
            str = str + dataContainer + '\n';
        }
        return str;
    }


    public void searchfile(List<Class> listik, int nummy, int hours, DataContainer dataContainer) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        Failiki failiki = new Failiki();
        failiki.creatlog();
        List<Method> listm = new ArrayList<>();
        Object s = null;
        listik = listik.stream().filter(x -> ((Days) x.getAnnotation(Days.class)).weekdays() == nummy).collect(Collectors.toList());
        for (Class lis : listik) {
            s = lis.newInstance();
            listm = List.of(lis.getDeclaredMethods());
        }
        listm = listm.stream().filter(x -> ((AnnotationTime) x.getAnnotation(AnnotationTime.class)).hour() == hours).collect(Collectors.toList());
        listm = listm.stream().sorted(Comparator.comparingInt(x -> ((AnnotationTime) x.getAnnotation(AnnotationTime.class)).priority())).toList();
        for (Method lism : listm) {
            lism.invoke(s, dataContainer);
            failiki.filewrting(dataContainer.toString());
        }

    }

    public String reflection(int hour, int data, DataContainer dataContainer) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        List<Class> persons = new ArrayList<>();
        persons = initialization(persons);
        String str = search(persons, data, hour, dataContainer);
        searchfile(persons, data, hour, dataContainer);
        return str;
    }

}
