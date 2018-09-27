package vsushko.runtime;

/**
 * Java program to illustrate traceMethodCalls() method of Runtime class
 * <p>
 * This method enables or disables tracing of method calls
 *
 * @author vsushko
 */
public class TraceMethodCalls {

    public static void main(String[] args) {
        // start tracing for instructions
        System.out.println("Enabling..");

        Runtime.getRuntime().traceMethodCalls(true);
        System.out.println("Enabled");
    }
}
