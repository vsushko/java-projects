package com.vsushko.masterspringbatch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * @author vsushko
 */
@Component
public class InMemeItemProcessor implements ItemProcessor<Integer, Integer> {

    @Override
    public Integer process(Integer item) {
        return Integer.sum(10, item);
    }
}
