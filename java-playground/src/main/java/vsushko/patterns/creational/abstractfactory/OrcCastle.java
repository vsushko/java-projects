package vsushko.patterns.creational.abstractfactory;

/**
 * @author vsushko
 */
public class OrcCastle implements Castle {
    private static final String DESCRIPTION = "This is the Orc castle!";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
