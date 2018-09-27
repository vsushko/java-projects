package vsushko.fromjavainaction;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class Foo {

    public static void main(String[] args) {
        IntStream.rangeClosed(2, 6)
                .forEach(n -> System.out.println("Hello " + n + " bottles of beer"));

        Set<Integer> numbers = new HashSet<>(Arrays.asList(1, 1, 2, 3, 5, 8));
        Set<Integer> newNumbers = Collections.unmodifiableSet(numbers);
        // throws UnsupportedOperationException
        // newNumbers.add(9);

        // instantiates pairs explicitly
        Pair<String, String> raoul = new Pair<>("Raoul", "+ 44 007007007");
        Pair<String, String> alan = new Pair<>("Alan", "+44 003133700");
    }
}

class Pair<X, Y> {
    public final X x;
    public final Y y;

    public Pair(X x, Y y) {
        this.x = x;
        this.y = y;
    }
}