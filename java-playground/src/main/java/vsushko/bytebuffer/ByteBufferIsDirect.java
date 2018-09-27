package vsushko.bytebuffer;

import java.nio.ByteBuffer;

/**
 * @author vsushko
 */
public class ByteBufferIsDirect {

    public static void main(String[] argv) {
        ByteBuffer bbuf = ByteBuffer.allocate(10);
        int capacity = bbuf.capacity(); // 10
        System.out.println(capacity);
        bbuf.putShort(2, (short) 123);
        System.out.println(bbuf.isDirect());
    }
}
