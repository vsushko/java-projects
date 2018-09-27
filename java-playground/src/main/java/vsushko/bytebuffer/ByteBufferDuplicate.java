package vsushko.bytebuffer;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * @author vsushko
 */
public class ByteBufferDuplicate {
    public static void main(String[] argv) throws Exception {
        ByteBuffer bbuf = ByteBuffer.allocate(10);
        int capacity = bbuf.capacity(); // 10
        System.out.println(capacity);
        bbuf.putShort(2,(short)123);

        ByteBuffer bb = bbuf.duplicate();

        System.out.println(Arrays.toString(bb.array()));
    }
}
