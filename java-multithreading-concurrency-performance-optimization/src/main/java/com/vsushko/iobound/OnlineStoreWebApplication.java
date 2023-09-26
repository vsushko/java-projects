package com.vsushko.iobound;

/**
 * @author vsushko
 */
public class OnlineStoreWebApplication {
    /*

    private HttpClient httpClient;

    // Starts an HTTP Server listening on port 8080.
    // Delegates the handling of the requests to the handleHttpRequest method
    private void startHttpServer() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        HttpContext context = server.createContext("/");
        context.setHandler(this::handleHttpRequest);

        // UPDATE
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        this.httpClient = HttpClient.newBuilder().executor(executorService).build();
        server.setExecutor(executorService);

        server.start();
    }

    //  UPDATED - Handles an incoming HTTP request from a user
    private void handleHttpRequest(HttpExchange httpExchange) {
        int numberOfProducts = parseRequest(httpExchange);
        URI requestURI = URI.create(String.format("best-online-store/products?number-of-products=%d", numberOfProducts);

        CompletableFuture<HttpResponse<String>> responseFuture =
                httpClient.sendAsync(HttpRequest.newBuilder()
                                .GET()
                                .uri(requestURI)
                                .build(),
                        HttpResponse.BodyHandlers.ofString());

        responseFuture.thenAccept( response -> {
            sendWebpageToUser(httpExchange, response);
        });
    }
    */
}
