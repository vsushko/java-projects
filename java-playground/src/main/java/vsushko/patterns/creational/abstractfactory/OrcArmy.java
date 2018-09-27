package vsushko.patterns.creational.abstractfactory;

/**
 * @author vsushko
 */
public class OrcArmy implements Army {
    private static final String DESCRIPTION = "This is the Orc Army!";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}

