package com.udemy.sortingalgorithms;

import java.util.Arrays;

/**
 * @author vsushko
 */
public class MergeSort {
    private static int[] nums = new int[]{2, 1, 4, 3};
    private static int[] temp = new int[nums.length];

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        mergeSort(0, nums.length - 1);
        long endTime = System.currentTimeMillis();
        System.out.println("taken time " + (endTime - startTime));
        System.out.println(Arrays.toString(nums));
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
