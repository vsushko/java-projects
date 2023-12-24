package com.vsushko.grpc.blog.server;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 * @author vsushko
 */
public class BlogServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        int port = 50051;

        MongoClient client = MongoClients.create("mongodb://localhost:27017/");

        Server server = ServerBuilder.forPort(port)
                .addService(new BlogServiceImpl(client))
                .build();

        server.start();

        System.out.println("Server started...");
        System.out.println("Listening on port: " + port);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Received Shutdown request");
            server.shutdown();
            client.close();
            System.out.println("Server stopped");
        }));

        server.awaitTermination();
    }
}
