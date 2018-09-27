package vsushko.patterns.creational.prototype;

public abstract class Mage extends Prototype {
    @Override
    public abstract Mage copy() throws CloneNotSupportedException;
}
