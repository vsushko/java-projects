package com.vsushko.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * @author vsushko
 */
public class Main {

    public static void main(String[] args) {
        System.out.println(genericMethod("Apple", "Orange"));
        System.out.println(genericMethod(100, 200));
        System.out.println(genericMethod('A', 'B'));

        Product<Integer, String> product = new Product<>(121, "Soap");
        int pId = product.getId();
        String pDescription = product.getDescription();

        System.out.println("Product Id: " + pId);
        System.out.println("Product description: " + pDescription);

        Product<String, String> product2 = new Product<>("str1", "str2");
        System.out.println("Product Id: " + product2.getId());
        System.out.println("Product description: " + product2.getDescription());

        // Product.Mapping<Integer, String> map = new Product.Mapping<>(1, "One");

        Integer[] inteArray = new Integer[]{1, 2, 3, 4, 5};
        Character[] charArray = new Character[]{'A', 'B', 'C'};

        printData(inteArray);
        printData(charArray);

        System.out.println(map(1, "One"));
        System.out.println(map("Hello", "World"));

        ShapeClass<Integer> shape = new ShapeClass<>();
        shape.setT(100);
        System.out.println("Data is: " + shape.getT());

        ShapeClass shape1 = shape;
        shape1.setT("Hello");
        shape = shape1;
        System.out.println("Data is: " + shape.getT());

        inspect(1.5);

        Integer display = display(1000, 200);
        System.out.println(display);

        Integer max = new FindMaximum().findMax(10, 210, 30);
        System.out.println(max);

        List<Integer> ints = IntStream.range(1, 100).boxed().collect(Collectors.toList());
        List<Double> doubles = IntStream.range(1, 100).asDoubleStream().boxed().collect(Collectors.toList());
        List<Long> longs = LongStream.range(1, 100).boxed().collect(Collectors.toList());
        calculateSum(ints);
        calculateSum(doubles);
        calculateSum(longs);

        List<Family> families = new ArrayList<>();
        List<Grandfather> grandfathers = new ArrayList<>();
        List<Father> fathers = new ArrayList<>();
        List<Son> sons = new ArrayList<>();
        List<Grandson> grandsons = new ArrayList<>();
        grandsons.add(new Grandson());
        familyTree(grandsons);

        List<Object> collect = IntStream.range(1, 100).boxed().collect(Collectors.toList());
        display(collect);

        addMember(fathers);

        List<Integer> collect1 = IntStream.range(1, 100).boxed().collect(Collectors.toList());
        List<Double> collect2 = IntStream.range(1, 100).asDoubleStream().boxed().collect(Collectors.toList());

        showData(collect1);
        showData(collect2);

        List<Student> students = Arrays.asList(new Student("John"), new Student("Nancy"));
        showData(students);

        GenericClass<String> gc = new GenericClass<>("Hello");
        System.out.println(gc);

        Calculate<Long> calc = new Calculate<>(123L);
        System.out.println(calc);

        // No primitive
        // No instance
        // No static field
        // No casting
        // No instance of
        // No array
        // No exception

    }

    public static void showData(List<?> list) {
        for (Object li : list) {
            System.out.println(li);
        }
    }

    public static void addMember(List<? super Father> list) {
        list.add(new Son());
        list.add(new Father());
        //list.add(new Grandfather());
        list.add(new Grandson());
    }

    public static void display(List<? super Number> list) {
        for (Object number : list) {
            System.out.println(number);
        }
    }

    public static void familyTree(List<? extends Family> families) {
        for (Family family : families) {
            System.out.println("Class is " + family.getClass().getName());
        }
    }

    public static void calculateSum(List<? extends Number> list) {
        double sum = 0;

        for (Number li : list) {
            sum = sum + li.doubleValue();
        }
        System.out.println("Total is: " + sum);
    }

    public static <T extends Number> void inspect(T t) {
        System.out.println("T type is: " + t.getClass().getName());
    }

    public static <T extends Number & Comparable> T display(T t1, T t2) {
        return t1.compareTo(t2) > 0 ? t1 : t2;
    }

    public static <T> void printData(T[] t) {
        for (T data : t) {
            System.out.print(data + " ");
        }
        System.out.println();
    }

    public static <K, V> Map<K, V> map(K key, V value) {
        Map<K, V> map = new HashMap<>();
        map.put(key, value);
        return map;
    }

    private static <T> List<T> genericMethod(T s1, T s2) {
        List<T> list = new ArrayList<>();
        list.add(s1);
        list.add(s2);
        return list;
    }

    static class Student {
        String name;

        public Student(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    static class GenericClass<T> {
        T t;

        public GenericClass(T t) {
            this.t = t;
        }

        public T getT() {
            return t;
        }

        public void setT(T t) {
            this.t = t;
        }

        @Override
        public String toString() {
            return "GenericClass{" +
                    "t=" + t +
                    '}';
        }
    }
}

class FindMaximum {
    public <T extends Comparable> T findMax(T t1, T t2, T t3) {
        T max = t1;
        if (t2.compareTo(max) > 0) {
            max = t2;
        }
        if (t2.compareTo(max) > 0) {
            max = t3;
        }
        return max;
    }
}

class ShapeClass<T> {
    T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}

class Calculate<T extends Number> {
    T t;

    public Calculate(T t) {
        this.t = t;
    }

    @Override
    public String toString() {
        return "Calculate{" +
                "t=" + t +
                '}';
    }
}

class Family {
}

class Grandfather extends Family {
}

class Father extends Grandfather {
}

class Son extends Father {
}

class Grandson extends Son {
}

class Product<T, U> {
    private T id;
    private U description;

    public Product(T id, U description) {
        this.id = id;
        this.description = description;
    }

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    public U getDescription() {
        return description;
    }

    public void setDescription(U description) {
        this.description = description;
    }

    class Mapping<K, V> {
        private K k;
        private V v;

        public Mapping(K k, V v) {
            this.k = k;
            this.v = v;
        }

        public K getK() {
            return k;
        }

        public void setK(K k) {
            this.k = k;
        }

        public V getV() {
            return v;
        }

        public void setV(V v) {
            this.v = v;
        }
    }
}