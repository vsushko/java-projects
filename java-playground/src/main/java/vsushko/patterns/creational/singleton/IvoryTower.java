package vsushko.patterns.creational.singleton;

public final class IvoryTower {
    private static final IvoryTower INSTANCE = new IvoryTower();

    private IvoryTower() {
    }

    public static IvoryTower getInstance() {
        return INSTANCE;
    }
}
