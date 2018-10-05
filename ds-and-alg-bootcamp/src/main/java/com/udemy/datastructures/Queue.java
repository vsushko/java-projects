package com.udemy.datastructures;

/**
 * @author vsushko
 */
public class Queue {

    private class Node {
        private int data;
        private Node next;

        private Node(int data) {
            this.data = data;
        }
    }

    private Node head; // remove things here
    private Node tail; // add things here

    public boolean isEmpty() {
        return head == null;
    }

    public int peek() {
        return head.data;
    }

    public void add(int data) {
        // create a new node
        // set the current tail.next to point to this new node
        // then set the new node to be the new tail

        Node newNode = new Node(data);
        if (tail != null) {
            tail.next = newNode;
        }

        tail = newNode;

        // handle case of first element where head is null
        if (head == null) {
            head = tail;
        }
    }

    public int remove() {
        // save the data
        // Point the head to the next element (effectively removing it)
        // then return the data
        int data = head.data;
        head = head.next;

        // handle queue now being empty
        if (head == null) {
            tail = null;
        }
        return data;
    }
}
