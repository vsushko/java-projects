package com.udemy.datastructures;

/**
 * @author vsushko
 */
public class DynamicArray<String> {

    private Object[] data;
    private int size;
    private int initialCapacity;

    public DynamicArray(int initialCapacity) {
        this.initialCapacity = initialCapacity;
        data = new Object[initialCapacity];
    }

    public String get(int index) {
        return (String) data[index];
    }

    public void set(int index, String value) {
        data[index] = value;
    }

    // does the insert at the end of the array
    public void add(String value) {
        if (size == initialCapacity) {
            resize();
        }
        data[size] = value;
        size++;
    }

    public void insert(int index, String value) {
        // check size
        if (size == initialCapacity) {
            resize();
        }
        // copy up
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        // insert
        data[index] = value;
        size++;
    }

    private void resize() {
        Object[] newData = new Object[initialCapacity * 2];

        for (int i = 0; i < initialCapacity; i++) {
            newData[i] = data[i];
        }
        data = newData;
        initialCapacity = initialCapacity * 2;
    }

    public int size() {
        return size;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.print(data[i] + " ");
        }
    }

    public void delete(int index) {
        // copy down
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(String value) {
        for (int i = 0; i < size; i++) {
            String currentValue = (String) data[i];
            if (currentValue.equals(value)) {
                return true;
            }
        }
        return false;
    }
}
