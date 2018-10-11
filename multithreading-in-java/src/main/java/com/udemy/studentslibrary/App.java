package com.udemy.studentslibrary;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author vsushko
 */
public class App {
    private static final int NUMBER_OF_STUDENTS = 5;
    private static final int NUMBER_OF_BOOKS = 7;

    public static void main(String[] args) {
        Student[] students;
        Book[] books;
        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_STUDENTS);
        try {
            books = new Book[NUMBER_OF_BOOKS];
            students = new Student[NUMBER_OF_STUDENTS];

            for (int i = 0; i < NUMBER_OF_BOOKS; i++) {
                books[i] = new Book(i);
            }

            for (int i = 0; i < NUMBER_OF_STUDENTS; i++) {
                students[i] = new Student(i, books);
                executorService.execute(students[i]);
            }
        } finally {
            executorService.shutdown();
        }
    }
}
