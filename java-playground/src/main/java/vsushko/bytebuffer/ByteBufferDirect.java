package vsushko.bytebuffer;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * @author vsushko
 */
public class ByteBufferDirect {
    public static void main(String[] argv) {
        byte[] bytes = new byte[10];
        ByteBuffer buf = ByteBuffer.allocateDirect(10);
        buf = ByteBuffer.wrap(bytes);

        System.out.println(Arrays.toString(buf.array()));
        System.out.println(buf.toString());
    }
}
