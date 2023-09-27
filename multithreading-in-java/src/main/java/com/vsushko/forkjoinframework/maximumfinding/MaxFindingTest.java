package com.vsushko.forkjoinframework.maximumfinding;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

/**
 * @author vsushko
 */
public class MaxFindingTest {
    public static int THRESHOLD = 0;

    public static void main(String[] args) {
        long[] nums = initializeNums();
        THRESHOLD = nums.length / Runtime.getRuntime().availableProcessors();

        SequentialMaxFinding normalMaxFind = new SequentialMaxFinding();

        long start = System.currentTimeMillis();
        System.out.println("Max: " + normalMaxFind.sequentialMaxFind(nums, nums.length));
        System.out.println("Time taken: " + (System.currentTimeMillis() - start) + "ms");

        System.out.println();

        ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        ParallelMaxFinding findTask = new ParallelMaxFinding(nums, 0, nums.length);

        start = System.currentTimeMillis();
        System.out.println("Max: " + forkJoinPool.invoke(findTask));
        System.out.println("Time taken: " + (System.currentTimeMillis() - start) + "ms");
    }

    private static long[] initializeNums() {
        Random random = new Random();

        int arraySize = 10000000;
        long[] nums = new long[arraySize];
        for (int i = 0; i < arraySize; i++) {
            nums[i] = random.nextInt(1000);
        }
        return nums;
    }
}
