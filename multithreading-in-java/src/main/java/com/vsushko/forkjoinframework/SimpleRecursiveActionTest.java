package com.vsushko.forkjoinframework;

import java.util.concurrent.ForkJoinPool;

/**
 * @author vsushko
 */
public class SimpleRecursiveActionTest {

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());

        SimpleRecursiveAction action = new SimpleRecursiveAction(200);
        pool.invoke(action);
    }
}
