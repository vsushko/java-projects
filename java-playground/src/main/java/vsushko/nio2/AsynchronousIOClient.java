package vsushko.nio2;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author vsushko
 */
public class AsynchronousIOClient {
    private int port = 8080;
    private String hostName = "localhost";

    public static void main(String[] args) {
        //start client
        AsynchronousIOClient client = new AsynchronousIOClient();
        client.sendRequest("send info");
    }

    public void sendRequest(String request) {
        try {
            AsynchronousSocketChannel asc = AsynchronousSocketChannel.open();

            InetSocketAddress addr = new InetSocketAddress(hostName, port);
            Future<Void> conn = asc.connect(addr);

            //check if connected to server
            while (!conn.isDone()) {
                //do something
                //or sleep
                Thread.sleep(20);
            }

            //send request using write asynchronous operation
            byte reqb[] = request.getBytes();
            ByteBuffer bb = ByteBuffer.allocate(reqb.length);
            bb.put(request.getBytes());
            bb.rewind();

            Future<Integer> writeFuture = asc.write(bb);
            int count = writeFuture.get(30, TimeUnit.MILLISECONDS);
            System.out.println("byte sent to server " + count);

            //getResponse using read asynchronous operation and completion handler
            final ByteBuffer bbr = ByteBuffer.allocate(100);
            asc.read(bbr, null, new CompletionHandler<Integer, Object>() {
                @Override
                public void completed(Integer result, Object attachment) {
                    bbr.flip();
                    System.out.println("response from server " + result + " "
                            + Charset.defaultCharset()
                            .decode(bbr).toString());
                }

                @Override
                public void failed(Throwable exc, Object attachment) {
                    System.out.println("failed to get response from server ");
                }
            });

            Thread.sleep(1000);
            asc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
