package vsushko.bytebuffer;

import java.nio.ByteBuffer;

/**
 * @author vsushko
 */
public class ByteBufferAsShortBuffer {
    private static final int BSIZE = 1024;

    public static void main(String[] args) {
        ByteBuffer bb = ByteBuffer.allocate(BSIZE);

        bb.asShortBuffer().put((short) 471142);
        System.out.println(bb.getShort());
    }
}
