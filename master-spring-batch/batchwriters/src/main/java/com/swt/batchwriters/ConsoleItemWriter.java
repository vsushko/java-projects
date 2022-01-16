package com.swt.batchwriters;

import org.springframework.batch.item.support.AbstractItemStreamItemWriter;

import java.util.List;

/**
 * @author vsushko
 */
public class ConsoleItemWriter extends AbstractItemStreamItemWriter {
    @Override
    public void write(List items) throws Exception {
        items.forEach(System.out::println);
    }
}