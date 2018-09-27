package vsushko.runtime;

/**
 * This method Loads the specified filename as a dynamic library
 * <p>
 * Java program to illustrate load() method of Runtime class
 *
 * @author vsushko
 */
public class Load {

    public static void main(String[] args) {
        // load a library that is home/saket/Desktop folder
        Runtime.getRuntime().loadLibrary("/home/saket/Desktop/File");

        System.out.println("Library Loaded Successfully");
    }
}
