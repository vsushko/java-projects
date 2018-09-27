package vsushko.runtime;

/**
 * Java program to illustrate gc() method of Runtime class
 * <p>
 * This method runs the garbage collector
 *
 * @author vsushko
 */
public class GC {

    public static void main(String[] args) {
        // run the garbage collector
        Runtime.getRuntime().gc();

        System.out.println("Running");
    }
}
