package com.udemy.datastructures;

/**
 * @author vsushko
 */
public class LinkedList {
    public class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    public Node head;

    public void addFront(int data) {
        // create new node
        Node newNode = new Node(data);

        // if head...
        if (head == null) {
            head = newNode;
            return;
        }
        // set it's next to current head
        newNode.next = head;
        // set current head equal to this new head
        head = newNode;
    }

    public int getFirst() {
        return head.data;
    }

    public int getLast() {
        if (head == null) {
            throw new IllegalStateException("Empty list!");
        }
        Node current = head;

        // while we are not at the tail
        while (current.next != null) {
            current = current.next;
        }

        // we are at the tail
        return current.data;
    }

    public void addBack(int data) {
        Node newNode = new Node(data);

        // if head... set and return
        if (head == null) {
            head = newNode;
            return;
        }

        // else starting at head...
        Node current = head;

        // walk until to hit tail
        while (current.next != null) {
            current = current.next;
        }

        // set current node to equal newNode
        current.next = newNode;
    }

    public int size() {
        if (head == null) {
            return 0;
        }

        int count = 1;
        Node current = head;

        while (current.next != null) {
            current = current.next;
            count++;
        }
        return count;
    }

    public void clean() {
        head = null;
    }

    public void deleteValue(int data) {
        if (head == null) {
            return;
        }
        if (head.data == data) {
            head = head.next;
            return;
        }
        // else walk the list
        Node current = head;

        while (current.next != null) {
            if (current.next.data == data) {
                current.next = current.next.next;
                return;
            }
            current = current.next;
        }
    }

    public void clear() {
        head = null;
    }

    public void print() {
        Node current = head;

        while (current != null) {
            System.out.println(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
}
