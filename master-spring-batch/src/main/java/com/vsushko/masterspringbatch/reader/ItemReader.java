package com.vsushko.masterspringbatch.reader;

import org.springframework.batch.item.support.AbstractItemStreamItemReader;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author vsushko
 */
@Component
public class ItemReader extends AbstractItemStreamItemReader {

    Integer[] arrayStr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    List<Integer> list = Arrays.asList(arrayStr);

    int index = 0;

    @Override
    public Object read() {
        Integer nextItem = null;

        if (index < list.size()) {
            nextItem = list.get(index);
            index++;
        } else {
            index = 0;
        }
        return nextItem;
    }
}
