package vsushko.runtime;

/**
 * Java program to illustrate traceInstructions() method of Runtime class
 * <p>
 * This method enables or disables tracing of instructions
 *
 * @author vsushko
 */
public class TraceInstructions {

    public static void main(String[] args) {
        // start tracing for instructions
        Runtime.getRuntime().traceInstructions(true);

        System.out.println("Enabled");
        Runtime.getRuntime().traceInstructions(false);
        System.out.println("Disabled");
    }
}
