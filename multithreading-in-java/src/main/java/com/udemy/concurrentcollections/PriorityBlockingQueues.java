package com.udemy.concurrentcollections;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author vsushko
 */
public class PriorityBlockingQueues {

    public static void main(String[] args) {
        BlockingQueue<Person> queue = new PriorityBlockingQueue<Person>();

        new Thread(new FirstWorker(queue)).start();
        new Thread(new SecondWorker(queue)).start();
    }

    static class FirstWorker implements Runnable {
        private BlockingQueue<Person> blockingQueue;

        public FirstWorker(BlockingQueue<Person> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        public void run() {
            try {
                blockingQueue.put(new Person(12, "Adam"));
                blockingQueue.put(new Person(45, "Joe"));
                blockingQueue.put(new Person(78, "Daniel"));
                Thread.sleep(1000);
                blockingQueue.put(new Person(32, "Noel"));
                Thread.sleep(1000);
                blockingQueue.put(new Person(34, "Kevin"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class SecondWorker implements Runnable {
        private BlockingQueue<Person> blockingQueue;

        public SecondWorker(BlockingQueue<Person> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        public void run() {
            try {
                Thread.sleep(3000);
                System.out.println(blockingQueue.take());
                Thread.sleep(1000);
                System.out.println(blockingQueue.take());
                Thread.sleep(1000);
                System.out.println(blockingQueue.take());
                System.out.println(blockingQueue.take());
                System.out.println(blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Person implements Comparable<Person> {
        private int age;
        private String name;

        public Person(int age, String name) {
            this.age = age;
            this.name = name;
        }

        public int compareTo(Person person) {
            // return name.compareTo(person.name);
            return Integer.compare(this.age, person.getAge());
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name + " - " + age;
        }
    }
}
