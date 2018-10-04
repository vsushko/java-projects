package com.udemy.datastructures;

/**
 * @author vsushko
 */
public class SingleLinkedList<E> {
    private static class Node<E> {
        E item;
        Node<E> next;

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }

    public Node head;
    public int size;

    public void push(E e) {
        Node newNode = new Node<>(e, null);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public int lastIndexOf(E e) {
        int index = 0;

        Node current = head;
        while (current.next != null) {
            if (current.item == e) {
                return index;
            }
            index++;
            current = current.next;
        }
        // check the last node
        if (current.item == e) {
            return index;
        }
        return -1;
    }

    public E peek() {
        Node<E> f = head;
        return (f == null) ? null : f.item;
    }

    public E pop() {
        // get the head
        // assign head to its's next
        // return head
        if (head == null) {
            return null;
        }
        Node<E> first = head;
        head = first.next;
        size--;

        return first.item;
    }

    public boolean remove(E e) {
        // walk the list from the beginning
        // delete and return true if found
        // return false

        // head
        Node prev;
        Node current = head;

        if (current.item.equals(e)) {
            // make the next element the new head
            head = current.next;
            size--;
            return true;
        }

        // all others
        while (current.next != null) {
            prev = current;
            current = current.next;

            if (current.item.equals(e)) {
                // connect the previous node next to this nodes next
                // thereby by passing this current node we want to delete
                prev.next = current.next;
                size--;
                return true;
            }
        }
        return false;
    }

    public void set(int index, E e) {
        Node newNode = new Node<>(e, null);

        // head
        if (index == 0) {
            newNode.next = head.next;
            head = newNode;
            return;
        }

        // all others
        // take the previous node
        // attach it's next to this new node
        // attach this new node to current next node

        Node prev = head;
        Node current = head.next;

        for (int i = 1; i <= index; i++) {
            if (i == index) {
                prev.next = newNode;
                newNode.next = current.next;
                return;
            } else {
                prev = current;
                current = current.next;
            }
        }
    }
}
