package com.udemy.algorithms;

/**
 * @author vsushko
 */
public class FibonacciNaive {

    public static void main(String[] args) {
        System.out.println(fib(1000));
    }

    private static int fib(int n) {
        System.out.println("n = " + n);
        if (n <= 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }
}
