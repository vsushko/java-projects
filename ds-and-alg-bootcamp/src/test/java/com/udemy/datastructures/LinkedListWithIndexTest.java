package com.udemy.datastructures;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author vsushko
 */
public class LinkedListWithIndexTest {

    private LinkedListWithIndex linkedList;

    @Before
    public void setUp() {
        linkedList = new LinkedListWithIndex();
    }

    @Test
    public void getByIndex() {
        linkedList.addFront(99, 1);
        linkedList.addFront(100, 2);
        linkedList.addFront(101, 3);

        Assert.assertEquals(1, linkedList.getByIndex(99));
        Assert.assertEquals(2, linkedList.getByIndex(100));
        Assert.assertEquals(3, linkedList.getByIndex(101));
    }
}