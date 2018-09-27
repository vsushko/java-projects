package vsushko.datastructures;

public class LinkedList {
    Node head;  // head of list

    /* Linked list Node.  This inner class is made static so that
       main() can access it */
    static class Node {
        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        } // Constructor
    }

    /* method to create a simple linked list with 3 nodes*/
    public static void main(String[] args) {
        /* Start with the empty list. */
        LinkedList llist = new LinkedList();
        llist.head = new Node(1);
        Node second = new Node(2);
        Node third = new Node(3);

        llist.head.next = second; // Link first node with the second node
        second.next = third; // Link first node with the second node

        llist.printList();
    }

    /* This function prints contents of linked list starting from head */
    public void printList() {
        Node n = head;
        while (n != null) {
            System.out.print(n.data + " ");
            n = n.next;
        }
    }


    public void append(int new_data) {
        /* 1. Allocate the Node & 2. Put in the data 3. Set next as null */
        Node new_node = new Node(new_data);
        /* 4. If the Linked List is empty, then make the
           new node as head */
        if (head == null) {
            head = new Node(new_data);
            return;
        }
        /* 4. This new node is going to be the last node, so make next of it as null */
        new_node.next = null;
        /* 5. Else traverse till the last node */
        Node last = head;
        while (last.next != null) {
            last = last.next;
        }
        /* 6. Change the next of last node */
        last.next = new_node;
        return;
    }

    /* Given a key, deletes the first occurrence of key in linked list */
    private void deleteNode(int key) {
        // Store head node
        Node temp = head, prev = null;

        // If head node itself holds the key to be deleted
        if (temp != null && temp.data == key) {
            head = temp.next; // Changed head
            return;
        }
        // Search for the key to be deleted, keep track of the
        // previous node as we need to change temp.next
        while (temp != null && temp.data != key) {
            prev = temp;
            temp = temp.next;
        }
        // If key was not present in linked list
        if (temp == null) {
            return;
        }
        // Unlink the node from linked list
        prev.next = temp.next;
    }
}