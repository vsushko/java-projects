package vsushko.patterns.creational.abstractfactory;

/**
 * @author vsushko
 */
public class ElfCastle implements Castle {
    private static final String DESCRIPTION = "This is the Elven castle!";

    @Override
    public String getDescription() {

        return DESCRIPTION;
    }
}
