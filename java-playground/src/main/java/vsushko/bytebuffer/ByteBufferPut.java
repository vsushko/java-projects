package vsushko.bytebuffer;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * @author vsushko
 */
public class ByteBufferPut {

    public static void main(String[] args) {
        ByteBuffer bbuf = ByteBuffer.allocate(10);
        int capacity = bbuf.capacity(); // 10
        System.out.println(capacity);
        bbuf.put((byte) 0xFF);
        bbuf.position(5);
        bbuf.put((byte) 0xEE);
        System.out.println(Arrays.toString(bbuf.array()));
    }
}
