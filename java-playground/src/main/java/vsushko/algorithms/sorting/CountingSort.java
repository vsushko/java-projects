package vsushko.algorithms.sorting;

import java.util.Arrays;

/**
 * @author vsushko
 */
public class CountingSort {

    public static void main(String[] args) {
        char[] array = {'g', 'e', 'e', 'k', 's', 'f', 'o',
                'r', 'g', 'e', 'e', 'k', 's'};
        sort(array);
        System.out.print("Sorted character array is ");
        System.out.println(Arrays.toString(array));
    }

    private static void sort(char[] array) {
        int n = array.length;
        // the output character array that will have sorted array
        char[] output = new char[n];
        // create a count array to store count of individual
        // characters and initialize count array as 0
        int[] count = new int[256];
        for (int i = 0; i < 256; i++) {
            count[i] = 0;
        }
        // store count of each character
        for (int i = 0; i < n; i++) {
            ++count[array[i]];
        }
        // change count[i] so that count[i] now contains actual position
        // of this character in output array
        for (int i = 1; i < 255; i++) {
            count[i] += count[i - 1];
        }
        // build the output character array
        for (int i = 0; i < n; i++) {
            output[count[array[i]]-1] = array[i];
            --count[array[i]];
        }
        // Copy the output array to arr, so that arr now
        // contains sorted characters
        for (int i = 0; i < n; ++i) {
            array[i] = output[i];
        }
    }
}
