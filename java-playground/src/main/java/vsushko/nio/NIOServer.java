package vsushko.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author vsushko
 */
public class NIOServer {

    public static void main(String[] args) throws IOException {

        // Selector: multiplexor of SelectableChannel objects
        Selector selector = Selector.open();

        // ServerSocketChannel: selectable channel for stream-oriented listening sockets
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        InetSocketAddress socketAddress = new InetSocketAddress("localhost", 8080);

        // Binds the channel's socket to a local address and configures the socket to listen for connections
        socketChannel.bind(socketAddress);

        // Adjusts this channel's blocking mode
        socketChannel.configureBlocking(false);

        int ops = socketChannel.validOps();
        SelectionKey selectionKey = socketChannel.register(selector, ops, null);

        // Infinite loop..
        // Keep server running
        while (true) {
            System.out.println("i'm a server and i'm waiting for new connection and buffer select...");
            // Selects a set of keys whose corresponding channels are ready for I/O operations
            selector.select();

            // token representing the registration of a SelectableChannel with a Selector
            Set<SelectionKey> keySet = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keySet.iterator();

            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();

                // Tests whether this key's channel is ready to accept a new socket connection
                if (key.isAcceptable()) {
                    SocketChannel client = socketChannel.accept();

                    // Adjusts this channel's blocking mode to false
                    client.configureBlocking(false);

                    // Operation-set bit for read operations
                    client.register(selector, SelectionKey.OP_READ);
                    System.out.println("Connection Accepted: " + client.getLocalAddress() + "\n");

                    // Tests whether this key's channel is ready for reading
                } else if (key.isReadable()) {
                    SocketChannel client = (SocketChannel) key.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(256);
                    client.read(byteBuffer);

                    String result = new String(byteBuffer.array()).trim();
                    System.out.println("Message received: " + result);

                    if (result.equals("signal")) {
                        client.close();
                        System.out.println("\nIt's time to close connection as we got result 'signal'");
                        System.out.println("\nServer will keep running. Try running client again to establish new connection");
                    }
                }
                iterator.remove();
            }
        }
    }
}
