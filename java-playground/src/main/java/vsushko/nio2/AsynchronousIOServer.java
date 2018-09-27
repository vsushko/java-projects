package vsushko.nio2;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.concurrent.Future;

/**
 * @author vsushko
 */
public class AsynchronousIOServer {

    private int port = 8080;

    public static void main(String[] args) {
        //run server
        AsynchronousIOServer server = new AsynchronousIOServer();
        server.startAsyncServer();
    }

    //starts server and listens for client connect
    public void startAsyncServer() {

        try {
            AsynchronousServerSocketChannel assc =
                    AsynchronousServerSocketChannel.open()
                            .bind(new InetSocketAddress(port));

            while (true) {

                //get future object which returns AsynchronousSocketChannel
                Future<AsynchronousSocketChannel> socketFuture = assc.accept();

                //check if client connection is available, if not sleep
                while (!socketFuture.isDone()) {
                    //sleep or do something else
                    Thread.sleep(200);
                }

                //get AsynchronousSocketChannel
                //object from Future object using get
                AsynchronousSocketChannel asc = socketFuture.get();

                //read data from client
                ByteBuffer bb = ByteBuffer.allocate(1200);
                int byteCount = asc.read(bb).get();
                System.out.println("bytes read from client " + byteCount);

                bb.flip();
                String clientInput = Charset.defaultCharset().
                        decode(bb).toString();
                System.out.println(clientInput);

                //write response to client
                ByteBuffer bbOut = ByteBuffer.wrap("take the info".getBytes());
                bbOut.rewind();
                //write asynchronous operation using CompletionHandler
                asc.write(bbOut, null, new CompletionHandler<Integer, Object>() {
                    @Override
                    public void completed(Integer result, Object attachment) {
                        System.out.println("number of bytes sent to client "
                                + result);
                    }

                    @Override
                    public void failed(Throwable exc, Object attachment) {
                        System.out.println("failed to send resp"
                                + attachment);
                    }
                });
                Thread.sleep(1000);
                asc.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}