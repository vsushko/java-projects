package vsushko.patterns.creational.abstractfactory;

/**
 * @author vsushko
 */
public class ElfKing implements King {
    private static final String DESCRIPTION = "This is the Elven king!";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}

