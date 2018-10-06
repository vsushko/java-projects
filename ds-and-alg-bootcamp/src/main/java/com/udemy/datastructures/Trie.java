package com.udemy.datastructures;

import java.util.HashMap;
import java.util.Map;

/**
 * @author vsushko
 */
public class Trie {

    class TrieNode {
        char c;
        boolean isWholeWord;
        Map<Character, TrieNode> children = new HashMap<>();

        public TrieNode() {
        }

        public TrieNode(char c) {
            this.c = c;
        }
    }

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        Map<Character, TrieNode> children = root.children;

        // for each letter in the word string...
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            // see if there is a node already representing this letter

            // if there is get it, else creat it with it's own internal Map of letters
            TrieNode n;
            if (children.containsKey(c)) {
                n = children.get(c);
            } else {
                n = new TrieNode(c);
                children.put(c, n);
            }

            // this is the magic line here..
            children = n.children;

            // Take this newly created, or found, node
            // And reset the children variable to point to it's children
            //
            // This is how we continuously step further down the try
            // and insert more letters (even repeating letters) deeper in the
            // data structure.
            //
            // Now when we loop again, we will will loop from here
            // And insert the next letters from this starting point in the word

            // set this flag to represent that this node is also a full word
            if (i == word.length() - 1) {
                n.isWholeWord = true;
            }
        }
    }

    // returns if the word is in the trie
    public boolean contains(String word) {
        TrieNode node = containsNode(word);
        return node != null && node.isWholeWord;
    }

    // returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        return containsNode(prefix) != null;
    }

    public TrieNode containsNode(String str) {
        // This method walks the string one letter at a time
        // continuously returning a found nodes children,
        // so long as there is a match.

        // If there is a match, it returns the final node.
        // Else it returns null - meaning no match.
        Map<Character, TrieNode> children = root.children;
        TrieNode node = null;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (children.containsKey(c)) {
                node = children.get(c);
                children = node.children;
            } else {
                return null;
            }
        }

        return node;
    }
}
