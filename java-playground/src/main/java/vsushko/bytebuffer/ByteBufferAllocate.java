package vsushko.bytebuffer;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * @author vsushko
 */
public class ByteBufferAllocate {
    public static void main(String[] argv) throws Exception {
        byte[] bytes = new byte[10];
        ByteBuffer buf = ByteBuffer.allocate(10);
        buf = ByteBuffer.wrap(bytes);

        System.out.println(Arrays.toString(buf.array()));
        System.out.println(buf.toString());
    }
}
