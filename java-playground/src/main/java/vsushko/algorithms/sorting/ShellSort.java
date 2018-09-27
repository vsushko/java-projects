package vsushko.algorithms.sorting;

import java.util.Arrays;

/**
 * @author vsushko
 */
public class ShellSort {

    public static void main(String[] args) {
        int arr[] = {12, 34, 54, 2, 3};
        System.out.println("Array before sorting");
        System.out.println(Arrays.toString(arr));
        sort(arr);
        System.out.println("Array after sorting");
        System.out.println(Arrays.toString(arr));
    }

    // function to sort array using shell sort
    private static void sort(int[] array) {
        int n = array.length;

        //start with a big gap, then reduce the gap
        for (int gap = n / 2; gap > 0; gap /= 2) {
            // do a gapped insertion sort for this gap size
            // the first gap elements a[0..gap-1] are already in gapped order
            // keep adding one more element until the entire array is gap sorted
            for (int i = gap; i < n; i += 1) {
                // add a[i] to the elements that have been gap sorted save a[i]
                // in temp an maked a hole at position i
                int temp = array[i];

                // shift earlier gap-sorted elements up until
                // the correct location for a[i] is found
                int j;
                for (j = i; j >= gap && array[j - gap] > temp; j -= gap) {
                    array[j] = array[j - gap];
                }
                // put temp (the original a[i]) in its correct location
                array[j] = temp;
            }
        }
    }
}
