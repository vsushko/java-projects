package vsushko.patterns.creational.abstractfactory;

/**
 * @author vsushko
 */
public class ElfArmy implements Army {

    private static final String DESCRIPTION = "This is the Elven Army!";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
