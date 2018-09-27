package vsushko.bytebuffer;

import java.nio.ByteBuffer;

/**
 * @author vsushko
 */
public class ByteBufferAsLongBuffer {
    private static final int BSIZE = 1024;

    public static void main(String[] args) {
        ByteBuffer bb = ByteBuffer.allocate(BSIZE);
        bb.asLongBuffer().put(99472342341142L);
        System.out.println(bb.getLong());
    }
}
