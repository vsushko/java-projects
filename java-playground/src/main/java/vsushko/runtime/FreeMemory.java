package vsushko.runtime;

/**
 * Java program to illustrate freeMemory() method of Runtime class
 * <p>
 * This method returns the amount of free memory in the JVM(Java Virtual Machine)
 *
 * @author vsushko
 */
public class FreeMemory {

    public static void main(String[] args) {
        // print the number of free bytes
        System.out.println("" + Runtime.getRuntime().freeMemory());
    }
}
