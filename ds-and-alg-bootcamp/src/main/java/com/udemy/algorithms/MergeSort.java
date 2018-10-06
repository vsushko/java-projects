package com.udemy.algorithms;

import java.util.Arrays;

/**
 * Merge sort
 * <p>
 * pros: Pretty efficient sorting algorithm - O(n log(n))
 * cons: Takes up a bit more space (as you are copying and duplicating
 * contents of array)
 *
 * @author vsushko
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] arr = new int[]{7, 6, 5, 4, 2, 5, 6, 7, 2, 1, 8, 7, 5, 3, 2};
        System.out.println(Arrays.toString(arr));

        sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr, int l, int r) {
        System.out.println("splitting l r: " + l + " " + r);

        if (l < r) {
            // find the middle point
            int m = (l + r) / 2;

            // sort first and second halves
            sort(arr, l, m);
            sort(arr, m + 1, r);

            // merge the sorted halves
            merge(arr, l, m, r);
        }
    }

    // merges two sub arrays of arr[]
    // first subarray is arr[l..m]
    // second subarray is arr[m+1..r]
    private static void merge(int[] arr, int l, int m, int r) {
        System.out.println();
        System.out.println("merge l m r: " + l + " " + m + " " + r);

        // find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        // create temp arrays
        int[] L = new int[n1];
        int[] R = new int[n2];

        // copy data to temp arrays
        for (int i = 0; i < n1; i++) {
            L[i] = arr[l + i];
        }
        for (int i = 0; i < n2; i++) {
            R[i] = arr[m + 1 + i];
        }

        // merge the temp arrays
        int i = 0, j = 0;

        // initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // copy remaining elements of L[] if any
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        // copy remaining elements of R[] if any
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
        System.out.println("After merge");
        printArray(arr);
    }

    private static void printArray(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
