package vsushko.patterns.creational.builder;

/**
 * @author vsushko
 */
public enum HairColor {
    WHITE,
    BLOND,
    RED,
    BROWN,
    BLACK;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}

