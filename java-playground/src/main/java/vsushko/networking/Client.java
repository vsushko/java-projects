package vsushko.networking;

import javax.net.ssl.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.SecureRandom;

/**
 * @author vsushko
 */
public class Client {

    private static final String passphrase = "clientpw";

    // constructor to put ip address and port
    public Client(String address, int port) throws GeneralSecurityException, IOException {

        SecureRandom secureRandom = new SecureRandom();

        // client
        KeyStore clientKeyStore = KeyStore.getInstance("JKS");
        clientKeyStore.load(new FileInputStream("client.private"), passphrase.toCharArray());

        // server
        KeyStore serverKeyStore = KeyStore.getInstance("JKS");
        serverKeyStore.load(new FileInputStream("server.public"), "public".toCharArray());

        //
        TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
        tmf.init(serverKeyStore);

        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
        kmf.init(clientKeyStore, passphrase.toCharArray());

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), secureRandom);

        // establish a connection
        SSLSocketFactory sf = sslContext.getSocketFactory();
        SSLSocket socket = (SSLSocket) sf.createSocket(address, port);
        System.out.println("Connected");

        DataInputStream din = new DataInputStream(System.in);
        DataOutputStream dout = new DataOutputStream(socket.getOutputStream());

        String input = "";
        try {
            while (!"quit".equals(input)) {
                input = din.readLine();
                dout.writeUTF(input);
            }
        } catch (IOException i) {
            System.out.println(i);
        }
    }

    public static void main(String args[]) throws GeneralSecurityException, IOException {
        Client client = new Client("127.0.0.1", 8181);
    }
}
