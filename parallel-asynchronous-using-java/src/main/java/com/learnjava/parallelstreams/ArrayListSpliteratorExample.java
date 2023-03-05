package com.learnjava.parallelstreams;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;

/**
 * @author vsushko
 */
public class ArrayListSpliteratorExample {

    public List<Integer> multiplyEachValue(List<Integer> inputList, int multiplyValue, boolean isParallel) {
        startTimer();
        Stream<Integer> integerStream = inputList.stream();
        if (isParallel) {
            integerStream.parallel();
        }
        List<Integer> resultList = integerStream
                .map(integer -> integer * multiplyValue)
                .collect(Collectors.toList());
        timeTaken();
        return resultList;
    }
}
