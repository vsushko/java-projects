package com.vsushko.multithreading;

/**
 * @author vsushko
 */
public class StackOverflowError {

    public static void main(String[] args) {
        new StackOverflowError().print(0);
    }

    public synchronized void print(int i) {
        //System.out.println(i++);
        print(i);
    }
}
