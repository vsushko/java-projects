package vsushko.runtime;

/**
 * Java program to illustrate halt() method of Runtime class
 * <p>
 * This method forcibly terminates the currently running Java virtual machine
 *
 * @author vsushko
 */
public class Halt {

    public static void main(String[] args) {
        // halt this process
        Runtime.getRuntime().halt(0);

        // print a string, just to see if it process is halted
        System.out.println("Process is still running.");
    }
}
