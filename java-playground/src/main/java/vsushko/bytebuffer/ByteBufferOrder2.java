package vsushko.bytebuffer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * @author vsushko
 */
public class ByteBufferOrder2 {

    public static void main(String[] argv) throws Exception {
        ByteBuffer bbuf = ByteBuffer.allocate(10);
        int capacity = bbuf.capacity(); // 10
        System.out.println(capacity);
        bbuf.putShort(2, (short) 123);
        bbuf.order(ByteOrder.LITTLE_ENDIAN);
        ByteOrder byteOrder = bbuf.order();

        System.out.println(byteOrder);
    }
}
