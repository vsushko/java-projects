package vsushko.runtime;

/**
 * Java program to illustrate maxMemory() method of Runtime class
 * <p>
 * This method returns the maximum amount of memory that the Java virtual machine will attempt to use
 *
 * @author vsushko
 */
public class MaxMemory {

    public static void main(String[] args) {
        // print the maximum memory
        System.out.println("" + Runtime.getRuntime().maxMemory());
    }
}
