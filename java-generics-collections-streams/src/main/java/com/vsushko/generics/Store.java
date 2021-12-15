package com.vsushko.generics;

/**
 * @author vsushko
 */
public class Store<T extends Number> {
    T value;

    public Store(T value) {
        this.value = value;
    }

    /**
     * @return the {@link #value}
     */
    public T getValue() {
        return value;
    }

    /**
     * @param value the {@link #value}  to set
     */
    public void setValue(T value) {
        this.value = value;
    }
}
