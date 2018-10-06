package com.udemy.algorithms;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author vsushko
 */
public class FibonacciTest {

    private FibonacciNaive naive;
    private FibonacciMemorized memo;

    @Before
    public void setUp() {
        naive = new FibonacciNaive();
        memo = new FibonacciMemorized();
    }

    @Test
    public void naive() {
        Assert.assertEquals(0, FibonacciNaive.fib(0));
        Assert.assertEquals(1, FibonacciNaive.fib(1));
        Assert.assertEquals(1, FibonacciNaive.fib(2));
        Assert.assertEquals(2, FibonacciNaive.fib(3));
        Assert.assertEquals(3, FibonacciNaive.fib(4));
        Assert.assertEquals(5, FibonacciNaive.fib(5));
        Assert.assertEquals(8, FibonacciNaive.fib(6));
        Assert.assertEquals(13, FibonacciNaive.fib(7));
        Assert.assertEquals(21, FibonacciNaive.fib(8));
    }

    @Test
    public void memorized() {
        Assert.assertEquals(0, memo.fib(0));
        Assert.assertEquals(1, memo.fib(1));
        Assert.assertEquals(1, memo.fib(2));
        Assert.assertEquals(2, memo.fib(3));
        Assert.assertEquals(3, memo.fib(4));
        Assert.assertEquals(5, memo.fib(5));
        Assert.assertEquals(8, memo.fib(6));
        Assert.assertEquals(13, memo.fib(7));
        Assert.assertEquals(21, memo.fib(8));
    }

    @Test
    public void recordTimeNaive() {
        long startTime = System.currentTimeMillis();
        FibonacciNaive.fib(30);
        long endTime = System.currentTimeMillis();
        long elapsedTime = (endTime - startTime) / 1000;
        System.out.println("elapsedTime = " + elapsedTime); // 19s
    }

    @Test
    public void recordTimeMemorized() {
        long startTime = System.currentTimeMillis();
        memo.fib(1000);
        long endTime = System.currentTimeMillis();
        long elapsedTime = (endTime - startTime) / 1000;
        System.out.println("elapsedTime = " + elapsedTime); // 20s
    }
}
