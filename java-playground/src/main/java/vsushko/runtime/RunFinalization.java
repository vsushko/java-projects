package vsushko.runtime;

/**
 * Java program to illustrate runFinalization() method of Runtime class
 * <p>
 * This method runs the finalization methods of any objects pending finalization
 *
 * @author vsushko
 */
public class RunFinalization {

    public static void main(String[] args) {
        // run the finalization
        Runtime.getRuntime().runFinalization();

        System.out.println("Finalized");
    }
}
