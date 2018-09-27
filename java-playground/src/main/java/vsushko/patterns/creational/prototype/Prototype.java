package vsushko.patterns.creational.prototype;

/**
 * Prototype
 */
public abstract class Prototype implements Cloneable {
    public abstract Object copy() throws CloneNotSupportedException;
}
