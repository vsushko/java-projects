package com.udemy.challenges.arraysandstrings;

import java.util.Arrays;

/**
 * @author vsushko
 */
public class PermutationDetector {

    public boolean isPermutation(String text, String perm) {
        if (perm.length() != text.length()) {
            return false;
        }
        return sort(text).equals(sort(perm));
    }

    private String sort(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
