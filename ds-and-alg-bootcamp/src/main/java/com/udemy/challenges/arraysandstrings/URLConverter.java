package com.udemy.challenges.arraysandstrings;

/**
 * @author vsushko
 */
public class URLConverter {

    public String urlify(String url, int length) {
        char[] result = new char[length];

        // strip off any space at beginning or end
        url = url.trim();

        char[] urlChars = url.toCharArray();
        int pointer = 0;

        for (int i = 0; i < urlChars.length; i++) {
            if (urlChars[i] != ' ') {
                result[pointer] = urlChars[i];
                pointer++;
            } else {
                result[pointer] = '%';
                result[pointer + 1] = '2';
                result[pointer + 2] = '0';
                pointer = pointer + 3;
            }
            prettyPrint(result);
            System.out.println("...");
        }
        return String.valueOf(result);
    }

    private void prettyPrint(char[] chars) {
        for (int i = 0; i < chars.length; i++) {
            System.out.print("c[" + chars[i] + "]");
        }
    }
}
