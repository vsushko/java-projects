package com.udemy.datastructures;

import java.util.Arrays;

/**
 * @author vsushko
 */
public class MaxIntHeap {

    private int capactity = 10;
    private int size = 0;

    public int[] items = new int[capactity];

    private int leftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    private int rightChildIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }

    private int parentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    private boolean hasLeftChild(int index) {
        return leftChildIndex(index) < size;
    }

    private boolean hasRightChild(int index) {
        return rightChildIndex(index) < size;
    }

    private boolean hasParent(int index) {
        return parentIndex(index) >= 0;
    }

    private int leftChild(int index) {
        return items[leftChildIndex(index)];
    }

    private int rightChild(int index) {
        return items[rightChildIndex(index)];
    }

    private int parent(int index) {
        return items[parentIndex(index)];
    }

    private void ensureCapacity() {
        if (size == capactity) {
            items = Arrays.copyOf(items, capactity * 2);
            capactity *= 2;
        }
    }

    public int extractMax() {
        if (size == 0) {
            throw new IllegalStateException();
        }

        // grab the max
        int item = items[0];
        // swap top and bottom
        items[0] = items[size - 1];
        size--;
        // reorder
        heapifyDown();
        // return max
        return item;
    }

    public void insert(int item) {
        ensureCapacity();
        // put in the last spot
        items[size] = item;
        size++;
        heapifyUp();
    }

    public void heapifyUp() {
        // start at last element
        int index = size - 1;
        // while my parents are less than me...
        while (hasParent(index) && parent(index) < items[index]) {
            swap(parentIndex(index), index);
            // walk upwards to next node
            index = parentIndex(index);
        }
    }

    public void heapifyDown() {
        // starting at the top
        int index = 0;

        // as long as I have children
        // note: only need to check left because if no left, there is no right
        while (hasLeftChild(index)) {

            // take the larger of the two indexes
            int smallerChildIndex = leftChildIndex(index);
            if (hasRightChild(index) && rightChild(index) > leftChild(index)) {
                smallerChildIndex = rightChildIndex(index);
            }

            // now compare

            // if I am smaller than the items of my two children...
            // then everything is good. I am sorted.
            if (items[index] > items[smallerChildIndex]) {
                break;
            } else {
                //  we are still not in order - swap
                swap(index, smallerChildIndex);
            }

            // then move down to smaller child
            index = smallerChildIndex;
        }
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(i + "[" + items[i] + "]");
        }
    }

    private void swap(int indexOne, int indexTwo) {
        int temp = items[indexOne];
        items[indexOne] = items[indexTwo];
        items[indexTwo] = temp;
    }
}
