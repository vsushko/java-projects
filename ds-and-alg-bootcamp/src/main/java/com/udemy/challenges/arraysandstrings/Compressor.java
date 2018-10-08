package com.udemy.challenges.arraysandstrings;

/**
 * @author vsushko
 */
public class Compressor {

    // This one is better for obvious reasons.
    // Tip: StringBuilder much faster than appending to String manually
    public String compress(String str) {
        StringBuilder compressed = new StringBuilder();
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            count++;

            // If next char different, append this result
            if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
                compressed.append(str.charAt(i));
                compressed.append(count);
                count = 0;
            }
        }
        return compressed.length() < str.length() ? compressed.toString() : str;
    }
}
