package vsushko.bytebuffer;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * @author vsushko
 */
public class ByteBufferSlice {

    public static void main(String[] args) {
        ByteBuffer bbuf = ByteBuffer.allocate(10);
        int capacity = bbuf.capacity(); // 10
        System.out.println(capacity);
        bbuf.putShort(2, (short) 123);

        ByteBuffer bb = bbuf.slice();

        System.out.println(Arrays.toString(bb.array()));
    }
}

