package vsushko.patterns.creational.abstractfactory;

/**
 * The factory of kingdom factories
 *
 * @author vsushko
 */
public class FactoryMaker {

    /**
     * The factory method to create KingdomFactory concrete objects.
     */
    public static KingdomFactory makeFactory(KingdomType type) {
        switch (type) {
            case ELF:
                return new ElfKingdomFactory();
            case ORC:
                return new OrcKingdomFactory();
            default:
                throw new IllegalArgumentException("KingdomType not supported.");
        }
    }

    /**
     * Enumeration for the different types of Kingdoms.
     */
    public enum KingdomType {
        ELF, ORC
    }
}
