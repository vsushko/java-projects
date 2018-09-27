package vsushko.bytebuffer;

import java.nio.ByteBuffer;

/**
 * @author vsushko
 */
public class ByteBufferAsDoubleBuffer {
    private static final int BSIZE = 1024;

    public static void main(String[] args) {
        ByteBuffer bb = ByteBuffer.allocate(BSIZE);

        bb.asDoubleBuffer().put(9947.1142);
        System.out.println(bb.getDouble());
    }
}
