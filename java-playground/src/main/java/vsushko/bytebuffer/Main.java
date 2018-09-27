package vsushko.bytebuffer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;

/**
 * @author vsushko
 */
public class Main {

    private static final int BSIZE = 1024;

    public static void main(String[] args) throws IOException {
        // A ByteBuffer is a fixed-capacity buffer that holds byte values
        byte[] bytes = new byte[10];
        ByteBuffer buf = ByteBuffer.wrap(bytes);

        // Create a ByteBuffer using a byte array
        ByteBuffer buf1 = ByteBuffer.wrap(bytes);

        // Create a non-direct ByteBuffer with a 10 byte capacity
        ByteBuffer buf2 = ByteBuffer.allocate(10);

        // Create a direct (memory-mapped) ByteBuffer with a 10 byte capacity
        ByteBuffer buf3 = ByteBuffer.allocateDirect(10);

        // Allocation automatically zeroes the ByteBuffer
        ByteBuffer bb = ByteBuffer.allocate(BSIZE);
        int i = 0;
        while (i++ < bb.limit()) {
            if (bb.get() != 0) {
                System.out.println("nonzero");
            }
        }
        System.out.println("i = " + i);

        // Store and read a char array
        ByteBuffer bb1 = ByteBuffer.allocate(BSIZE);
        bb1.asCharBuffer().put("Howdy!");
        char c;
        while ((c = bb1.getChar()) != 0) {
            System.out.print(c + " ");
        }
        System.out.println();

        // Store and read a short
        // Rewind a ByteBuffer
        ByteBuffer bb3 = ByteBuffer.allocate(BSIZE);
        bb3.rewind();

        bb3.asShortBuffer().put((short) 471142);
        System.out.println(bb3.getShort());

        // Store and read an int
        ByteBuffer bb4 = ByteBuffer.allocate(BSIZE);
        bb4.asIntBuffer().put(99471142);
        System.out.println(bb4.getInt());

        // Store and read a long
        ByteBuffer bb5 = ByteBuffer.allocate(BSIZE);
        bb5.asLongBuffer().put(99472342341142L);
        System.out.println(bb5.getLong());

        // Store and read a float
        ByteBuffer bb6 = ByteBuffer.allocate(BSIZE);
        bb6.asFloatBuffer().put(99471142);
        System.out.println(bb6.getFloat());

        // Store and read a double
        ByteBuffer bb7 = ByteBuffer.allocate(BSIZE);
        bb7.asDoubleBuffer().put(99471142);
        System.out.println(bb7.getDouble());

        // ByteBuffer.hasRemaining()
        // Use while loop to read a ByteBuffer
        ByteBuffer bb8 = ByteBuffer.wrap(new byte[]{0, 0, 0, 0, 0, 0, 0, 'a'});
        bb8.rewind();
        System.out.println("Byte Buffer");
        while (bb8.hasRemaining()) {
            System.out.println(bb8.position() + " -> " + bb8.get());
        }

        // Retrieve all bytes in the buffer
        ByteBuffer bb9 = ByteBuffer.wrap(bytes);
        bb9.clear();
        bytes = new byte[bb9.capacity()];
        bb9.get(bytes, 0, bytes.length);

        // Retrieve bytes between the position and limit
        ByteBuffer bb10 = ByteBuffer.wrap(bytes);
        bytes = new byte[bb10.remaining()];
        bb10.get(bytes, 0, bytes.length);

        // Converting text to and from ByteBuffers with UTF-16BE
        FileChannel fc1 = new FileOutputStream("data2.txt").getChannel();
        fc1.write(ByteBuffer.wrap("Some text".getBytes("UTF-16BE")));
        fc1.close();
        ByteBuffer bb11 = ByteBuffer.allocate(BSIZE);
        // Now try reading again:
        fc1 = new FileInputStream("data2.txt").getChannel();
        bb11.clear();
        fc1.read(bb11);
        bb11.flip();
        System.out.println(bb11.asCharBuffer());

        // Converting text to and from ByteBuffers
        FileChannel fc2 = new FileOutputStream("data2.txt").getChannel();
        fc2.write(ByteBuffer.wrap("Some text".getBytes()));
        fc2.close();
        fc2 = new FileInputStream("data2.txt").getChannel();
        ByteBuffer bb12 = ByteBuffer.allocate(BSIZE);
        fc2.read(bb12);
        bb12.flip();

        System.out.println(bb12.asCharBuffer());
        // Decode using this system's default Charset:
        bb12.rewind();
        String encoding = System.getProperty("file.encoding");
        System.out.println("Decoded using " + encoding + ": " + Charset.forName(encoding).decode(bb12));
        // Or, we could encode with something that will print:
        fc2 = new FileOutputStream("data2.txt").getChannel();
        fc2.write(ByteBuffer.wrap("Some text".getBytes("UTF-16BE")));
        fc2.close();

        // Get the ByteBuffer's capacity
        ByteBuffer bb13 = ByteBuffer.allocateDirect(10);
        int capacity = bb13.capacity();
        System.out.println("capacity=" + capacity);

        // Use the absolute get().
        ByteBuffer bb14 = ByteBuffer.allocateDirect(10);
        byte b = bb14.get(5); // position=0
        System.out.println("position=" + b);

        // Set the position
        ByteBuffer bb15 = ByteBuffer.allocateDirect(10);
        bb15.position(5);

        // Use the relative get()
        ByteBuffer bb16 = ByteBuffer.allocateDirect(10);
        b = bb16.get();
        System.out.println(b);

        // Get remaining byte count in a ByteBuffer
        ByteBuffer bb17 = ByteBuffer.allocateDirect(10);
        int rem = bb17.remaining();
        System.out.println("remaining=" + rem);

        // Set the limit for ByteBuffer
        ByteBuffer bb18 = ByteBuffer.allocateDirect(10);
        System.out.println("remaining=" + bb18.limit(7));

        // This convenience method sets the position to 0
        ByteBuffer bb19 = ByteBuffer.allocateDirect(10);
        bb19.rewind(); // remaining=7

        // Converting Between a ByteBuffer an a Byte Array
        ByteBuffer bb20 = ByteBuffer.wrap(bytes);

        // Putting Bytes into a ByteBuffer
        ByteBuffer bb21 = ByteBuffer.allocate(10);
        capacity = bb21.capacity(); // 10
        System.out.println(capacity);
        bb21.put((byte) 0xFF);
        bb21.position(5);
        bb21.put((byte) 0xFF);
        int pos21 = bb21.position();
        int rem21 = bb21.remaining();
        bb21.limit(7);
        bb21.rewind();

        // Endian differences and data storage
        ByteBuffer bb22 = ByteBuffer.wrap(new byte[12]);
        bb22.asCharBuffer().put("abcdef");
        System.out.println(toString(bb22.array()));
        bb22.rewind();
        bb22.order(ByteOrder.BIG_ENDIAN);
        bb22.asCharBuffer().put("abcdef");
        System.out.println(toString(bb22.array()));
        bb22.rewind();
        bb22.order(ByteOrder.LITTLE_ENDIAN);
        bb22.asCharBuffer().put("abcdef");
        System.out.println(toString(bb22.array()));

        // Use FileChannel and ByteBuffer to Copy File
        String source = "s.txt";
        String destination = "d.txt";

        FileInputStream fis = new FileInputStream(source);
        FileOutputStream fos = new FileOutputStream(destination);

        FileChannel fci = fis.getChannel();
        FileChannel fco = fos.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (true) {
            int read = fci.read(buffer);

            if (read == -1) {
                break;
            }
            buffer.flip();
            fco.write(buffer);
            buffer.clear();
        }

        // Read from a channel with a ByteBuffer
        ReadableByteChannel channel = new FileInputStream("infile").getChannel();

        ByteBuffer bb25 = ByteBuffer.allocateDirect(10);

        int numRead = 0;
        while (numRead >= 0) {
            bb25.rewind();
            numRead = channel.read(bb25);
            bb25.rewind();
            for (int k = 0; k < numRead; k++) {
                byte b25 = bb25.get();
            }
        }

        // Test views of long elements in a ByteBuffer
        ByteBuffer bb26 = ByteBuffer.allocate(20);

        bb26.put((byte) 0x07);
        bb26.put((byte) 0x08);
        bb26.put((byte) 0x09);
        bb26.put((byte) 0x10);
        bb26.put((byte) 0x11);
        bb26.put((byte) 0x12);
        bb26.put((byte) 0x13);
        bb26.put((byte) 0x14);

        bb26.position(1).limit(5);
        bb26.mark();

        System.out.println("Expect an exception here");
        System.out.println("" + bb26.order().toString() + ": " + bb26.getLong());
    }
    private static String toString(byte[] a) {
        StringBuffer result = new StringBuffer("[");
        for (int i = 0; i < a.length; i++) {
            result.append(a[i]);
            if (i < a.length - 1)
                result.append(", ");
        }
        result.append("]");
        return result.toString();
    }
}
