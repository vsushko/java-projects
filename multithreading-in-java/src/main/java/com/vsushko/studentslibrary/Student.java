package com.vsushko.studentslibrary;

import java.util.Random;

/**
 * @author vsushko
 */
public class Student implements Runnable {
    private static final int NUMBER_OF_BOOKS = 7;

    private int id;
    private Book[] books;

    public Student(int id, Book[] books) {
        this.id = id;
        this.books = books;
    }

    @Override
    public void run() {
        Random random = new Random();

        while (true) {
            int bookId = random.nextInt(NUMBER_OF_BOOKS);
            try {
                books[bookId].read(this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                '}';
    }
}
