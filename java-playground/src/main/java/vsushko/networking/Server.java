package vsushko.networking;

import javax.net.ssl.*;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.SecureRandom;

/**
 * @author vsushko
 */
public class Server {

    private static final String passphrase = "serverpw";
    private final SSLContext sslContext;

    // constructor with port
    public Server() throws GeneralSecurityException, IOException {
        // starts server and waits for a connection

        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextInt();

        // client
        KeyStore clientKeyStore = KeyStore.getInstance("JKS");
        clientKeyStore.load(new FileInputStream("client.public"), "public".toCharArray());

        TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
        tmf.init(clientKeyStore);

        // server
        KeyStore serverKeyStore = KeyStore.getInstance("JKS");
        serverKeyStore.load(new FileInputStream("server.private"), passphrase.toCharArray());

        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
        kmf.init(serverKeyStore, passphrase.toCharArray());

        // ssl
        sslContext = SSLContext.getInstance("TLS");
        sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), secureRandom);
    }

    public static void main(String args[]) throws GeneralSecurityException, IOException {
        Server server = new Server();
        SSLServerSocket sslSocket = server.createSocket(8181);

        try {
            System.out.println("Waiting for a client ...");
            Socket socket = sslSocket.accept();
            System.out.println("Client accepted");
            DataInputStream din = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

            while (true) {
                System.out.println(din.readUTF());
            }
        } catch (IOException i) {
            System.out.println(i);
        }
    }

    private SSLServerSocket createSocket(int port) throws IOException {
        SSLServerSocketFactory sf = sslContext.getServerSocketFactory();
        SSLServerSocket ss = (SSLServerSocket) sf.createServerSocket(port);
        ss.setNeedClientAuth(true);
        System.out.println("Server started");
        return ss;
    }
}

