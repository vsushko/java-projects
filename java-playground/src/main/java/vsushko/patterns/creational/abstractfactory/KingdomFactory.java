package vsushko.patterns.creational.abstractfactory;

/**
 * @author vsushko
 */
public interface KingdomFactory {
    Castle createCastle();
    King createKing();
    Army createArmy();
}
