package com.udemy.sortingalgorithms;

import java.util.Arrays;
import java.util.Random;

/**
 * @author vsushko
 */
public class MergeSortParallel {
    private static int[] nums = new int[]{2, 1, 4, 3};
    private static int[] temp = new int[nums.length];

    public static void main(String[] args) {
        int numOfThreads = Runtime.getRuntime().availableProcessors();
        System.out.println(numOfThreads);

        long startTime = System.currentTimeMillis();
        parallelMergeSort(0, nums.length - 1, numOfThreads);
        long endTime = System.currentTimeMillis();
        System.out.println("taken time " + (endTime - startTime));
        System.out.println(Arrays.toString(nums));
    }

    public static int[] createRandomArray() {
        int[] a = new int[10000];

        Random random = new Random();
        for (int i = 0; i < a.length; i++) {
            a[i] = random.nextInt(100000);
        }
        return a;
    }

    private static Thread mergeSortParallel(final int low, final int high, final int numOfThreads) {
        return new Thread() {
            @Override
            public void run() {
                parallelMergeSort(low, high, numOfThreads / 2);
            }
        };
    }

    private static void parallelMergeSort(int low, int high, int numOfThreads) {
        if (numOfThreads <= 1) {
            mergeSort(low, high);
            return;
        }

        int middleIndex = (low + high) / 2;
        Thread leftSorter = mergeSortParallel(low, middleIndex, numOfThreads);
        Thread rightSorter = mergeSortParallel(middleIndex + 1, high, numOfThreads);
        leftSorter.start();
        rightSorter.start();

        try {
            leftSorter.join();
            rightSorter.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        merge(low, middleIndex, high);
    }

    private static void mergeSort(int low, int high) {
        if (low >= high) {
            return;
        }

        int middle = (low + high) / 2;

        mergeSort(low, middle);
        mergeSort(middle + 1, high);
        merge(low, middle, high);
    }

    private static void merge(int low, int middle, int high) {
        for (int i = low; i <= high; i++) {
            temp[i] = nums[i];
        }

        int i = low;
        int j = middle + 1;
        int k = low;

        while (i <= middle && j <= high) {
            if (temp[i] <= temp[j]) {
                nums[k] = temp[i];
                i++;
            } else {
                nums[k] = temp[j];
                j++;
            }
            k++;
        }

        while (i <= middle) {
            nums[k] = temp[i];
            k++;
            i++;
        }
        while (j <= high) {
            nums[k] = temp[j];
            k++;
            j++;
        }
    }
}
