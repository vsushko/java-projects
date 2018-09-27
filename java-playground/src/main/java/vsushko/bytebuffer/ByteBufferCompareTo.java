package vsushko.bytebuffer;

import java.nio.ByteBuffer;

/**
 * @author vsushko
 */
public class ByteBufferCompareTo {
    public static void main(String[] argv) throws Exception {
        ByteBuffer bbuf = ByteBuffer.allocate(10);
        int capacity = bbuf.capacity(); // 10
        System.out.println(capacity);
        bbuf.putShort(2, (short) 123);

        ByteBuffer bb = bbuf.duplicate();

        System.out.println(bb.compareTo(bb));
    }
}
