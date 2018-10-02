package com.udemy.valuesandreferences;

/**
 * @author vsushko
 */
public class MainTest {
    public static void main(String args[]) {
        MainTest main = new MainTest();
        main.start();
    }

    public void start() {
        String last = "Z";
        Container container = new Container();
        container.setInitial("C");
        another(container, last);
        System.out.print(container.getInitial());
    }

    public void another(Container initialHolder, String newInitial) {
        newInitial.toLowerCase();
        initialHolder.setInitial("B");
        Container initial2 = new Container();
        initialHolder = initial2;
        System.out.print(initialHolder.getInitial());
        System.out.print(newInitial);
    }
}
