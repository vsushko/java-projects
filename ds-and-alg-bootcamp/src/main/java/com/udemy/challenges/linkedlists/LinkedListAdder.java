package com.udemy.challenges.linkedlists;

import java.util.Stack;

/**
 * @author vsushko
 */
public class LinkedListAdder {

    // add numbers contained within two LinkedLists
    public Stack sum(Stack first, Stack second) {
        // convert
        int num1 = convertFrom(first);
        int num2 = convertFrom(second);

        int sum = num1 + num2;

        // put back into LinkedList form
        Stack total = convertTo(sum);

        return total;
    }

    private int convertFrom(Stack stack) {
        // pop the numbers off the stack and build them up into a string, then an int
        StringBuilder sb = new StringBuilder();

        while (!stack.isEmpty()) {
            int num = (int) stack.pop();
            sb.append(num);
        }

        return Integer.parseInt(sb.toString());
    }

    private Stack convertTo(int number) {
        // because we need them in reverse order, store them here first,
        // they flip to reverse order after
        Stack forward = new Stack();
        Stack backward = new Stack();

        // keep slicing off the digits by dividing by 10 and pushing the remainder
        while (number > 0) {
            forward.push(number % 10);
            number = number / 10;
        }

        // now put flip them over so they are reversed
        while (!forward.isEmpty()) {
            backward.push(forward.pop());
        }

        return backward;
    }
}
