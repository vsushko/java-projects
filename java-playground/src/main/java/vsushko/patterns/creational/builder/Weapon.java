package vsushko.patterns.creational.builder;

/**
 * @author vsushko
 */
public enum Weapon {
    DAGGER,
    SWORD,
    AXE,
    WARHAMMER,
    BOW;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
