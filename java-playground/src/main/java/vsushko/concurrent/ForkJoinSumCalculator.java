package vsushko.concurrent;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.function.Function;
import java.util.stream.LongStream;

/**
 * @author vsushko
 */
public class ForkJoinSumCalculator extends RecursiveTask {

    public static final long THRESHOLD = 10_000;

    public static final ForkJoinPool FORK_JOIN_POOL = new ForkJoinPool();

    private final long[] numbers;
    private final int start;
    private final int end;

    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    public ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start;
        if (length <= THRESHOLD) {
            return computeSequentially();
        }

        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length / 2);
        leftTask.fork();
        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length / 2, end);
        Long rightResult = rightTask.compute();
        Long leftResult = (Long) leftTask.join();

        return leftResult + rightResult;
    }

    private long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }

    private static long forkJoinSum(long toNumber) {
        long[] numbers = LongStream.rangeClosed(1, toNumber).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return FORK_JOIN_POOL.invoke(task);
    }

    public static void main(String[] args) {
        Function<Long, Long> forkJoinSum = ForkJoinSumCalculator::forkJoinSum;
        long result = forkJoinSum.apply(10_000_000L);
        System.out.println(result);
    }
}
