package com.vsushko.sumproblem;

import java.util.Random;

/**
 * @author vsushko
 */
public class App {

    public static void main(String[] args) {
        Random random = new Random();
        int numOfProcessors = Runtime.getRuntime().availableProcessors();
        int[] nums = new int[100000];

        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt(100);
        }

        long start = System.currentTimeMillis();
        System.out.println(new SequentialSum().sum(nums));
        long end = System.currentTimeMillis();
        System.out.println("Sequential sum takes: " + (end - start) + "ms");

        start = System.currentTimeMillis();
        ParallelSum parallelSum = new ParallelSum(numOfProcessors);
        System.out.println("Sum is: " + parallelSum.sum(nums));
        end = System.currentTimeMillis();
        System.out.println("Parallel sum takes: " + (end - start) + "ms");
    }
}
