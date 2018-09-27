package vsushko.algorithms.sorting;

/**
 * @author vsushko
 */
public class TreeSort {

    public static void main(String[] args) {
        int[] array = new int[]{5, 4, 7, 2, 11};
        Node node = initTree(array);
        inorderRec(node);
    }

    // this method mainly calls insertRec()
    private static Node insert(Node node, int key) {
        node = insertRec(node, key);
        return node;
    }

    // a recursive function to insert a new key in bst
    private static Node insertRec(Node root, int key) {
        // if the tree is empty, return a new node
        if (root == null) {
            root = new Node(key);
            return root;
        }
        // recur down the tree
        if (key < root.key) {
            root.left = insertRec(root.left, key);
        } else if (key > root.key) {
            root.right = insertRec(root.right, key);
        }
        return root;
    }

    // a function to do inorder traversal of bst
    private static void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.key + " ");
            inorderRec(root.right);
        }
    }

    private static Node initTree(int[] array) {
        Node node = new Node();
        for (int i = 0; i < array.length; i++) {
            node = insert(node, array[i]);
        }
        return node;
    }
}

class Node {
    int key;
    Node left, right;

    Node() {
    }

    Node(int key) {
        this.key = key;
    }
}