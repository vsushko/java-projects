package vsushko.concurrent;

import java.util.concurrent.Executor;

/**
 * @author vsushko
 */
public class ExecutorExample {

    public static void main(String[] args) {
        ExecutorExample.execute();
    }

    // use invoker to execute the task
    public static void execute() {
        Executor executor = new Invoker();
        executor.execute(() -> System.out.println("asdf"));
    }
}

// create an invoker to create the executor instance
class Invoker implements Executor {
    @Override
    public void execute(Runnable r) {
        r.run();
    }
}

