package vsushko.patterns.creational.prototype;

public abstract class Warlord extends Prototype {
    @Override
    public abstract Warlord copy() throws CloneNotSupportedException;
}
