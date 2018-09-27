package vsushko.patterns.behavioral.iterator;

/**
 * Iterator interface to be implemented by iterators over various data structures
 *
 * @param <T> generically typed for various objects
 */
public interface Iterator<T> {
    boolean hasNext();
    T next();
}
