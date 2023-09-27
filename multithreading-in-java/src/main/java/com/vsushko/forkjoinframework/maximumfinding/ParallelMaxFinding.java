package com.vsushko.forkjoinframework.maximumfinding;

import java.util.concurrent.RecursiveTask;

/**
 * @author vsushko
 */
public class ParallelMaxFinding extends RecursiveTask<Long> {
    private long[] nums;
    private int lowIndex;
    private int highIndex;

    public ParallelMaxFinding(long[] nums, int lowIndex, int highIndex) {
        this.nums = nums;
        this.lowIndex = lowIndex;
        this.highIndex = highIndex;
    }

    @Override
    protected Long compute() {
        if (highIndex - lowIndex < MaxFindingTest.THRESHOLD) {
            return sequentialMaxFind();
        } else {
            int middleIndex = (lowIndex + highIndex) / 2;
            ParallelMaxFinding task1 = new ParallelMaxFinding(nums, lowIndex, middleIndex);
            ParallelMaxFinding task2 = new ParallelMaxFinding(nums, middleIndex, highIndex);

            invokeAll(task1, task2);

            return Math.max(task1.join(), task2.join());
        }
    }

    private long sequentialMaxFind() {
        long max = nums[0];
        for (int i = lowIndex; i < highIndex; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        return max;
    }
}
