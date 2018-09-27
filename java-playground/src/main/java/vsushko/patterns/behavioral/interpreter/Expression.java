package vsushko.patterns.behavioral.interpreter;

/**
 * Expression
 */
public abstract class Expression {
    public abstract int interpret();

    @Override
    public abstract String toString();
}
