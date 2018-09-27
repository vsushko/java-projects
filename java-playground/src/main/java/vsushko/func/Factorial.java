package vsushko.func;

/**
 * @author vsushko
 */
import java.util.stream.LongStream;

public class Factorial {

    public static void main(String[] args) {
        System.out.println("factorialIterative: " + factorialIterative(4));
        System.out.println("factorialRecursive: " + factorialRecursive(4));
        System.out.println("factorialStreams: " + factorialStreams(4));
        System.out.println("factorialTailRecursive: " + factorialTailRecursive(4));
    }

    // iterative factorial
    private static int factorialIterative(int n) {
        int r = 1;
        for (int i = 1; i <= n; i++) {
            r *= i;
        }
        return r;
    }

    // recursive factorial
    private static long factorialRecursive(long n) {
        return n == 1 ? 1 : n * factorialRecursive(n - 1);
    }

    // stream factorial
    private static long factorialStreams(long n) {
        return LongStream.rangeClosed(1, n)
                .reduce(1, (long a, long b) -> a * b);
    }

    // tail-recursive factorial
    private static long factorialTailRecursive(long n) {
        return factorialHelper(1, n);
    }

    // this function is tail recursive because the recursive
    // call is the last thing that happens in the function
    // Java doesn't support this kind of optimization but this
    // optimization is eventual
    private static long factorialHelper(long acc, long n) {
        return n == 1 ? acc : factorialHelper(acc * n, n - 1);
    }
}
