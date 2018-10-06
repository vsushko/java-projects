package com.udemy.algorithms;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author vsushko
 */
public class MergeSortTest {

    private MergeSort mergeSort;

    @Before
    public void SetUp() {
        mergeSort = new MergeSort();
    }

    @Test
    public void Sort() {

        //           l                  r
        int arr[] = {4, 7, 14, 1, 3, 9, 17};

        int l = 0;              // left pointer
        int r = arr.length - 1; // right pointer

        MergeSort.sort(arr, l, r);

        Assert.assertEquals(1, arr[0]);
        Assert.assertEquals(3, arr[1]);
        Assert.assertEquals(4, arr[2]);
        Assert.assertEquals(7, arr[3]);
        Assert.assertEquals(9, arr[4]);
        Assert.assertEquals(14, arr[5]);
        Assert.assertEquals(17, arr[6]);

        System.out.println("\nSorted array");
        System.out.println(Arrays.toString(arr));
    }
}
