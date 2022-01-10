package com.vsushko.masterspringbatch.writer;

import org.springframework.batch.item.support.AbstractItemStreamItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author vsushko
 */
@Component
public class ConsoleItemWriter extends AbstractItemStreamItemWriter {

    @Override
    public void write(List items) {
        items.stream().forEach(System.out::println);
        System.out.println("**** writing each chunk ****");
    }
}
