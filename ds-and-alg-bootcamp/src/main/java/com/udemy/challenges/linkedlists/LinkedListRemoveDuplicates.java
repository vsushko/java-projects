package com.udemy.challenges.linkedlists;

import java.util.HashSet;
import java.util.Set;

/**
 * @author vsushko
 */
public class LinkedListRemoveDuplicates {

    private Node head;

    public void addBack(int data) {
        Node newNode = new Node(data);

        // if head... set and return
        if (head == null) {
            head = newNode;
            return;
        }

        // Else starting at head...
        Node current = head;

        // Walk until to hit tail
        while (current.next != null) {
            current = current.next;
        }

        // Set current node to equal newNode
        current.next = newNode;
    }

    public void removeDuplicates() {
        // starting at head
        removeDuplicates(head);
    }

    public void removeDuplicates(Node current) {
        Set<Integer> set = new HashSet<>();

        Node previous = new Node();

        while (current != null) {
            if (set.contains(current.data)) {
                // skip this duplicate
                previous.next = current.next;
            } else {
                set.add(current.data);
                previous = current;
            }
            current = current.next;
        }
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
}
