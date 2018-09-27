package vsushko.runtime;

/**
 * Java program to illustrate removeShutdownHook() method of Runtime class
 * <p>
 * This method de-registers a previously-registered virtual-machine shutdown hook
 *
 * @author vsushko
 */
public class RemoveShutdownHook {
    // a class that extends thread that is to be called when program is exiting
    static class Message extends Thread {
        public void run() {
            System.out.println("Program exiting");
        }
    }

    public static void main(String[] args) {
        try {
            Message p = new Message();
            // register Message as shutdown hook
            Runtime.getRuntime().addShutdownHook(p);

            // cause thread to sleep for 3 seconds
            System.out.println("Waiting for 5 seconds...");
            Thread.sleep(5000);

            // remove the hook
            Runtime.getRuntime().removeShutdownHook(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
