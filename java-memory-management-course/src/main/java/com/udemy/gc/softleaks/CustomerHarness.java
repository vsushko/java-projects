package com.udemy.gc.softleaks;

public class CustomerHarness {

    public static void main(String[] args) {
        CustomerManager cm = new CustomerManager();
        GenerateCustomerTask task = new GenerateCustomerTask(cm);
        for (int user = 0; user < 10; user++) {
            Thread t = new Thread(task);
            t.start();
        }

        // main thread is now acting as the monitoring thread
        while (true) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cm.howManyCustomers();
            System.out.println("Available memory: " + Runtime.getRuntime().freeMemory() / 1024 + "k");
        }
    }
}
