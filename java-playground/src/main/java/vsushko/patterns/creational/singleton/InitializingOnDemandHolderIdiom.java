package vsushko.patterns.creational.singleton;

/**
 * The Initialize-on-demand-holder idiom is a secure way of creating a lazy initialized singleton
 * object in Java.
 * <p>
 * The technique is as lazy as possible and works in all known versions of Java. It takes advantage
 * of language guarantees about class initialization, and will therefore work correctly in all
 * Java-compliant compilers and virtual machines.
 * <p>
 * The inner class is referenced no earlier (and therefore loaded no earlier by the class loader) than
 * the moment that getInstance() is called. Thus, this solution is thread-safe without requiring special
 * language constructs (i.e. volatile or synchronized).
 */
public final class InitializingOnDemandHolderIdiom {
    private InitializingOnDemandHolderIdiom() {
    }

    public static InitializingOnDemandHolderIdiom getInstance() {
        return HelperHolder.INSTANCE;
    }

    // provides the lazy-loaded singleton instance
    private static class HelperHolder {
        private static final InitializingOnDemandHolderIdiom INSTANCE =
                new InitializingOnDemandHolderIdiom();
    }
}
