package com.udemy.datastructures;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author vsushko
 */
public class BinarySearchTreeTest {

    private BinarySearchTree bst;

    @Before
    public void SetUp() {
        bst = new BinarySearchTree();
    }

    @Test
    public void insert() {
        bst.insert(5, "e");
        bst.insert(3, "c");
        bst.insert(2, "b");
        bst.insert(4, "d");
        bst.insert(7, "g");
        bst.insert(6, "f");
        bst.insert(8, "h");

        Assert.assertEquals("e", bst.find(5));
        Assert.assertEquals("c", bst.find(3));
        Assert.assertEquals("b", bst.find(2));
        Assert.assertEquals("d", bst.find(4));
        Assert.assertEquals("g", bst.find(7));
        Assert.assertEquals("f", bst.find(6));
        Assert.assertEquals("h", bst.find(8));
        Assert.assertEquals(null, bst.find(99));

        bst.prettyPrint();

        System.out.println();
        bst.printInOrderTraversal();
        System.out.println();
        bst.printPreOrderTraversal();
        System.out.println();
        bst.printPostOrderTraversal();
    }

    @Test
    public void minKey() {
        bst.insert(5, "e");
        bst.insert(3, "c");
        bst.insert(2, "b");

        Assert.assertEquals(2, bst.findMinKey());
    }

    @Test
    public void deleteNoChild() {
        bst.insert(5, "e");
        bst.insert(3, "c");
        bst.insert(2, "b");
        bst.insert(4, "d");
        bst.insert(7, "g");
        bst.insert(6, "f");
        bst.insert(8, "h");

        bst.delete(2);

        Assert.assertNull(bst.find(2));

        bst.prettyPrint();
    }

    @Test
    public void deleteOneChild() {
        bst.insert(5, "e");
        bst.insert(3, "c");
        bst.insert(2, "b");
        bst.insert(4, "d");
        bst.insert(7, "g");
        bst.insert(6, "f");
//        bst.insert(8, "h");

        bst.delete(7);

        Assert.assertNull(bst.find(7));

        bst.prettyPrint();
    }

    @Test
    public void deleteTwoChildren() {
        bst.insert(5, "e");
        bst.insert(3, "c");
        bst.insert(2, "b");
        bst.insert(4, "d");
        bst.insert(7, "g");
        bst.insert(6, "f");
        bst.insert(8, "h");

        bst.delete(7);

        Assert.assertNull(bst.find(7));

        bst.prettyPrint();
    }
}