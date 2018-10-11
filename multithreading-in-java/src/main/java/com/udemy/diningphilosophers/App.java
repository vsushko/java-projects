package com.udemy.diningphilosophers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author vsushko
 */
public class App {
    private static final int NUMBER_OF_PHILOSOPHERS = 5;
    private static final int NUMBER_OF_CHOPSTICKS = 5;
    private static final int SIMULATION_RUNNING_TIME = 20 * 1000;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = null;
        Philosopher[] philosophers = null;

        try {
            philosophers = new Philosopher[NUMBER_OF_CHOPSTICKS];
            Chopstick[] chopsticks = new Chopstick[NUMBER_OF_PHILOSOPHERS];

            for (int i = 0; i < NUMBER_OF_CHOPSTICKS; i++) {
                chopsticks[i] = new Chopstick(i);
            }

            executorService = Executors.newFixedThreadPool(NUMBER_OF_PHILOSOPHERS);

            for (int i = 0; i < NUMBER_OF_PHILOSOPHERS; i++) {
                philosophers[i] = new Philosopher(i, chopsticks[i],
                        chopsticks[(i + 1) % NUMBER_OF_CHOPSTICKS]);
                executorService.execute(philosophers[i]);
            }
            Thread.sleep(SIMULATION_RUNNING_TIME);

            for (Philosopher p : philosophers) {
                p.setFull(true);
            }
        } finally {
            executorService.shutdown();

            while (!executorService.isTerminated()) {
                Thread.sleep(1000);
            }
            for (Philosopher p : philosophers) {
                System.out.println(p + " eats " + p.getEatingCounter());
            }
        }
    }
}
