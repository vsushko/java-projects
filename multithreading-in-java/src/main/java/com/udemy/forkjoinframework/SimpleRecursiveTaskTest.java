package com.udemy.forkjoinframework;

import java.util.concurrent.ForkJoinPool;

/**
 * @author vsushko
 */
public class SimpleRecursiveTaskTest {

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        SimpleRecursiveTask task = new SimpleRecursiveTask(120);
        System.out.println(pool.invoke(task));
    }
}
