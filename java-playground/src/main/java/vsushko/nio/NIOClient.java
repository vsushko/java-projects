package vsushko.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vsushko
 */
public class NIOClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        InetSocketAddress socketAddress = new InetSocketAddress("localhost", 8080);
        SocketChannel socketChannel = SocketChannel.open(socketAddress);

        System.out.println("Connecting to Server on port 1111...");

        List<String> strings = new ArrayList<>();

        // create ArrayList with strings
        strings.add("telegram");
        strings.add("whatsapp");
        strings.add("viber");
        strings.add("signal");

        for (String s : strings) {
            byte[] sBytes = new String(s).getBytes();
            ByteBuffer buffer = ByteBuffer.wrap(sBytes);
            socketChannel.write(buffer);

            System.out.println("sending: " + s);

            buffer.clear();

            // wait for 2 seconds before sending next message
            Thread.sleep(2000);
        }
        socketChannel.close();
    }
}
