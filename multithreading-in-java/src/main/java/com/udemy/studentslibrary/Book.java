package com.udemy.studentslibrary;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author vsushko
 */
public class Book {
    private int id;
    private Lock lock;

    public Book(int id) {
        this.id = id;
        this.lock = new ReentrantLock();
    }

    public void read(Student student) throws InterruptedException {
        // lock.lock();
        lock.tryLock(1, TimeUnit.MINUTES);
        System.out.println(student + " starts reading " + this);
        Thread.sleep(2000);
        lock.unlock();
        System.out.println(student);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                '}';
    }
}
