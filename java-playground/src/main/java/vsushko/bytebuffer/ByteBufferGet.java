package vsushko.bytebuffer;

import java.nio.ByteBuffer;

/**
 * @author vsushko
 */
public class ByteBufferGet {
    private static final int BSIZE = 10;

    public static void main(String[] args) {
        ByteBuffer bb = ByteBuffer.allocate(BSIZE);
        int i = 0;
        while (i++ < bb.limit()) {
            if (bb.get() != 0) {
                System.out.println("nonzero");
            }
        }
        System.out.println("i = " + i);
    }
}
