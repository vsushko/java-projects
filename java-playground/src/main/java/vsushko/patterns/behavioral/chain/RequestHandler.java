package vsushko.patterns.behavioral.chain;

/**
 * RequestHandler
 */
public abstract class RequestHandler {
    private RequestHandler next;

    public RequestHandler(RequestHandler next) {
        this.next = next;
    }

    /**
     * Request handler
     */
    public void handleRequest(Request req) {
        if (next != null) {
            next.handleRequest(req);
        }
    }

    protected void printHandling(Request req) {
        System.out.println("{} handling request \"{}\"" + this + " " + req);
    }

    @Override
    public abstract String toString();
}
