package vsushko.runtime;

/**
 * Java program to illustrate getRuntime() method of Runtime class
 * <p>
 * This method returns the instance or Runtime object associated with the current Java application
 *
 * @author vsushko
 */
public class GetRuntime {

    public static void main(String[] args) {
        // get the current runtime associated with this process
        Runtime run = Runtime.getRuntime();
        // print the current free memory for this runtime
        System.out.println("" + run.freeMemory());
    }
}
