package com.udemy.algorithms;

import java.util.Arrays;

/**
 * @author vsushko
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = new int[]{7, 6, 5, 4, 2, 5, 6, 7, 2, 1, 8, 7, 5, 3, 2};
        System.out.println(Arrays.toString(arr));

        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static int[] sort(int[] array) {
        // Pick a pivot element randomly

        // Walk through the array making sure that all the elements
        // are smaller before the pivot, and that all elements after are bigger
        // and we do this in place! That's the killer feature. No extra array required.
        //
        // Then we repeat the process to the left and right portions over and over again
        // Then eventually our array becomes sorted.

        quickSort(array, 0, array.length - 1);

        return array;
    }

    private static void quickSort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }

        // Step 1: Pick a pivot element - we will choose the center
        // Better would be to choose left + (right-left)/2 (as this would avoid overflow error for large arrays i.e. 2GB))
        int pivot = array[(left + right) / 2];

        // Step 2: Partition the array around this pivot - return the index of the partition
        int index = partition(array, left, right, pivot);

        // Step 3: Sort on the left and the right side
        quickSort(array, left, index - 1);
        quickSort(array, index, right);
    }

    private static int partition(int[] array, int left, int right, int pivot) {
        // move the left and right pointers in towards each other
        while (left <= right) {
            // move left until you find an element bigger than the pivot
            while (array[left] < pivot) {
                left++;
            }
            // move right until you find an element smaller then the pivot
            while (array[right] > pivot) {
                right--;
            }
            // then swap
            if (left <= right) {
                swap(array, left, right);
                left++;
                right--;
            }
        }
        // When we get here, everything in this partition will be in the right order
        // Now we need to return next partition point - which for us will be left
        return left;
    }

    private static void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
}
