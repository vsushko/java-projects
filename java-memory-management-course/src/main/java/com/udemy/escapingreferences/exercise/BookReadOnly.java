package com.udemy.escapingreferences.exercise;

/**
 * @author vsushko
 */
public interface BookReadOnly {
    int getId();

    String getTitle();

    String getAuthor();

    String toString();

    //  check if price is immutable
    Price getPrice();
}
