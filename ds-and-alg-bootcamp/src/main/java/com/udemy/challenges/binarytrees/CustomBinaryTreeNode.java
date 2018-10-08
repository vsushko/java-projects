package com.udemy.challenges.binarytrees;

/**
 * @author vsushko
 */
public class CustomBinaryTreeNode {
    public int key;
    public CustomBinaryTreeNode left;
    public CustomBinaryTreeNode right;

    public CustomBinaryTreeNode(int key) {
        this.key = key;
        right = null;
        left = null;
    }
}
