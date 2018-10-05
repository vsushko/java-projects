package com.udemy.algorithms;

import java.util.Arrays;

/**
 * @author vsushko
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 4, 6, 7, 3, 2, 4, 5, 7, 1, 0};
        System.out.println(Arrays.toString(sort(arr)));
    }

    private static int[] sort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // swap temp and arr[i]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }
}
