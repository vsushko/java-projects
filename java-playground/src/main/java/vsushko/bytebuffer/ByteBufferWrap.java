package vsushko.bytebuffer;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * @author vsushko
 */
public class ByteBufferWrap {

    public static void main(String[] argv) {
        byte[] bytes = new byte[10];
        ByteBuffer buf = ByteBuffer.wrap(bytes);

        System.out.println(Arrays.toString(buf.array()));
        System.out.println(buf.toString());
    }
}
