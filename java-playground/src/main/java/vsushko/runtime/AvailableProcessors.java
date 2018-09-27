package vsushko.runtime;

/**
 * Java program to illustrate availableProcessors() method of Runtime class
 * <p>
 * This method returns the number of processors available to the JVM (Java virtual machine)
 *
 * @author vsushko
 */
public class AvailableProcessors {

    public static void main(String[] args) {
        // check the number of processors available
        System.out.println("" + Runtime.getRuntime().availableProcessors());
    }
}
