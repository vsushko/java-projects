package vsushko;

import java.util.Stack;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {
        System.out.println(isValid("()))"));
    }

    private static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }
        return stack.isEmpty();
    }

}
