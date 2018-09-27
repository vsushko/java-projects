package vsushko.algorithms.searching;

/**
 * @author vsushko
 */
public class InterpolationSearch {

    public static void main(String[] args) {
        int[] array = new int[]{10, 12, 13, 16, 18, 19, 20, 21, 22, 23,
                24, 33, 35, 42, 47};
        int x = 18;
        int index = interpolationSearch(array, x);
        if (index != -1) {
            System.out.println("Element found at index " + index);
        } else {
            System.out.println("Element not found.");
        }
    }

    // If x is present in arr[0..n-1], then returns index of it, else returns -1.
    private static int interpolationSearch(int[] arr, int x) {
        // Find indexes of two corners
        int lo = 0, hi = (arr.length - 1);

        // Since array is sorted, an element present
        // in array must be in range defined by corner
        while (lo <= hi && x >= arr[lo] && x <= arr[hi]) {
            // Probing the position with keeping uniform distribution in mind.
            int pos = lo + (((hi - lo) / (arr[hi] - arr[lo])) * (x - arr[lo]));
            // Condition of target found
            if (arr[pos] == x) {
                return pos;
            }
            // If x is larger, x is in upper part
            if (arr[pos] < x) {
                lo = pos + 1;
            } else {
                // If x is smaller, x is in the lower part
                hi = pos - 1;
            }
        }
        return -1;
    }
}
