package vsushko.bytebuffer;

import java.nio.ByteBuffer;

/**
 * @author vsushko
 */
public class ByteBufferAsReadOnlyBuffer {

    public static void main(String[] args) {
        ByteBuffer bbuf = ByteBuffer.allocate(10);
        int capacity = bbuf.capacity(); // 10
        System.out.println(capacity);
        bbuf.putShort(2, (short) 123);
        ByteBuffer buf = bbuf.asReadOnlyBuffer();
        System.out.println(buf.hashCode());
    }
}
