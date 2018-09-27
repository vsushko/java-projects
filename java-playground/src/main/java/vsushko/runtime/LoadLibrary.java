package vsushko.runtime;

/**
 * Java program to illustrate loadLibrary() method of Runtime class
 * <p>
 * This method loads the dynamic library with the specified library name
 *
 * @author vsushko
 */
public class LoadLibrary {

    public static void main(String[] args) {
        // load a library that is home/saket/Desktop folder
        Runtime.getRuntime().loadLibrary("/home/saket/Desktop/Library");

        System.out.println("Library Loaded Successfully");
    }
}
