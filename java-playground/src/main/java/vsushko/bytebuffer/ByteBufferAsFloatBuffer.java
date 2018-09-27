package vsushko.bytebuffer;

import java.nio.ByteBuffer;

/**
 * @author vsushko
 */
public class ByteBufferAsFloatBuffer {
    private static final int BSIZE = 1024;

    public static void main(String[] args) {
        ByteBuffer bb = ByteBuffer.allocate(BSIZE);

        bb.asFloatBuffer().put(994.7F);
        System.out.println(bb.getFloat());
    }
}
