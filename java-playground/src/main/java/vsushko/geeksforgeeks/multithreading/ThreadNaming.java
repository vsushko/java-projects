package vsushko.geeksforgeeks.multithreading;

/**
 * Java program to illustrate how to set the name of the thread
 * at time of thread creation
 *
 * @author vsushko
 */
public class ThreadNaming extends Thread {
    ThreadNaming(String name) {
        // call to constructor of the Thread class
        super(name);
    }

    @Override
    public void run() {
        System.out.println("Thread is running.......");
    }

    public static void main(String[] args) {

        // creating two treads
        ThreadNaming t1 = new ThreadNaming("Vasya");
        ThreadNaming t2 = new ThreadNaming("Petya");

        // getting the above created threads names
        System.out.println("Thread 1: " + t1.getName());
        System.out.println("Thread 2: " + t2.getName());

        t1.start();
        t2.start();

        ThreadNaming2 t3 = new ThreadNaming2();
        ThreadNaming2 t4 = new ThreadNaming2();

        // getting the above created threads names.
        System.out.println("Thread 3: " + t3.getName());
        System.out.println("Thread 4: " + t4.getName());

        t3.start();
        t4.start();

        // now changing the name of threads
        t3.setName("Masha");
        t4.setName("Dasha");

        // again getting the new names of the threads
        System.out.println("Thread names after changing the thread names");
        System.out.println("Thread 3: " + t3.getName());
        System.out.println("Thread 4: " + t4.getName());

        ThreadNaming3 t5 = new ThreadNaming3();
        ThreadNaming3 t6 = new ThreadNaming3();

        t5.start();
        t6.start();
    }
}

class ThreadNaming2 extends Thread {
    @Override
    public void run() {
        System.out.println("Thread is running.......");
    }
}

class ThreadNaming3 extends Thread {
    @Override
    public void run() {
        // getting the current thread's name
        System.out.println("Fetching current thread name..");
        System.out.println(Thread.currentThread().getName());
    }
}
