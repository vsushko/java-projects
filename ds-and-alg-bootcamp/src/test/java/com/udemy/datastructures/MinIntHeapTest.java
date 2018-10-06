package com.udemy.datastructures;

import com.udemy.datastructures.MinIntHeap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author vsushko
 */
public class MinIntHeapTest {
    private MinIntHeap minHeap;

    @Before
    public void setUp() {
        minHeap = new MinIntHeap();
        minHeap.insert(6);
        minHeap.insert(5);
        minHeap.insert(4);
        minHeap.insert(3);
        minHeap.insert(2);
        minHeap.insert(1);
    }

    @Test
    public void Insert() {
        // Remember: The array walks top down / left to right
        Assert.assertEquals(1, minHeap.items[0]);
        Assert.assertEquals(3, minHeap.items[1]);
        Assert.assertEquals(2, minHeap.items[2]);
        Assert.assertEquals(6, minHeap.items[3]);
        Assert.assertEquals(4, minHeap.items[4]);
        Assert.assertEquals(5, minHeap.items[5]);
    }

    @Test
    public void ExtractMin() {
        Assert.assertEquals(1, minHeap.extractMin());
        Assert.assertEquals(2, minHeap.extractMin());
        Assert.assertEquals(3, minHeap.extractMin());
        Assert.assertEquals(4, minHeap.extractMin());
        Assert.assertEquals(5, minHeap.extractMin());
        Assert.assertEquals(6, minHeap.extractMin());
    }
}
