package vsushko.runtime;

/**
 * Java program to illustrate exit() method of Runtime class
 * <p>
 * This method terminates the currently running Java virtual machine by initiating its shutdown sequence
 *
 * @author vsushko
 */
public class Exit {

    public static void main(String[] args) {
        // cause the program to exit
        Runtime.getRuntime().exit(0);

        //Nothing will run now.
        System.out.println("Program Running Check");
    }
}
