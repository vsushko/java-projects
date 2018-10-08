package com.udemy.challenges.linkedlists;

/**
 * @author vsushko
 */
public class LinkedListLoopDetector {

    public Node head;

    public void addBack(Node newNode) {

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

    public boolean hasLoop() {
        return hasLoop(head);
    }

    private boolean hasLoop(Node first) {
        Node slow = first;
        Node fast = first;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}
