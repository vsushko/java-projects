package vsushko.algorithms.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author vsushko
 */
public class BucketSort {
    private static final int DEFAULT_BUCKET_SIZE = 5;

    public static void main(String[] args) {
        Integer[] array = new Integer[]{97, 65, 56, 34, 65, 34};
        sort(array, DEFAULT_BUCKET_SIZE);
        System.out.println(Arrays.toString(array));
    }

    private static void sort(Integer[] array, int bucketSize) {
        if (array.length == 0) {
            return;
        }

        // determine minimum and maximum values
        Integer minValue = array[0];
        Integer maxValue = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < minValue) {
                minValue = array[i];
            } else if (array[i] > maxValue) {
                maxValue = array[i];
            }
        }
        // initialise buckets
        int bucketCount = (maxValue - minValue) / bucketSize + 1;
        List<List<Integer>> buckets = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        // distribute input array values into buckets
        for (int i = 0; i < array.length; i++) {
            buckets.get((array[i] - minValue) / bucketSize).add(array[i]);
        }

        // sort buckets and place back into input array
        int currentIndex = 0;
        for (int i = 0; i < buckets.size(); i++) {
            Integer[] bucketsArray = new Integer[buckets.get(i).size()];
            bucketsArray = buckets.get(i).toArray(bucketsArray);
            InsertionSort.sort(convert(bucketsArray));
            for (int j = 0; j < bucketsArray.length; j++) {
                array[currentIndex++] = bucketsArray[j];
            }
        }
    }

    private static int[] convert(Integer[] array) {
        return Arrays.stream(array).mapToInt(i -> i).toArray();
    }
}
