package com.learnjava.util;

import java.util.List;

/**
 * @author vsushko
 */
public class ReduceExample {

    public int reduce_sum_ParallelStream(List<Integer> inputList) {
        return inputList
                .parallelStream()
                .reduce(0, (x, y) -> x + y);
    }

    public int reduce_multiply_ParallelStream(List<Integer> inputList) {
        return inputList
                .parallelStream()
                .reduce(1, (x, y) -> x * y);
    }
}
