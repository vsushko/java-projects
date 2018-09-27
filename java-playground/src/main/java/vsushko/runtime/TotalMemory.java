package vsushko.runtime;

/**
 * Java program to illustrate totalMemory() method of Runtime class
 * <p>
 * This method returns the amount of total memory in the JVM(Java Virtual Machine)
 *
 * @author vsushko
 */
public class TotalMemory {

    public static void main(String[] args) {
        // print the number of total bytes
        System.out.println("" + Runtime.getRuntime().totalMemory());
    }
}
