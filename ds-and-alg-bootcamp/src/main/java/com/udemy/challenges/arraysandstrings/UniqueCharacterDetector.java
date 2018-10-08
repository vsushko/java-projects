package com.udemy.challenges.arraysandstrings;

/**
 * @author vsushko
 */
public class UniqueCharacterDetector {

    public boolean isUnique(String text) {
        if (text.length() > 128) {
            return false;
        }

        boolean[] charSet = new boolean[128];

        for (int i = 0; i < text.length(); i++) {
            int val = text.charAt(i);
            // already found
            if (charSet[val]) {
                return false;
            }
            charSet[val] = true;
        }
        return true;
    }
}
