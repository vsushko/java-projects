package vsushko.patterns.behavioral.templatemethod;

/**
 * SubtleMethod implementation of {@link StealingMethod}.
 */
public class SubtleMethod extends StealingMethod {
    @Override
    protected String pickTarget() {
        return "shop keeper";
    }

    @Override
    protected void confuseTarget(String target) {
        System.out.println("Approach the {} with tears running and hug him!" + target);
    }

    @Override
    protected void stealTheItem(String target) {
        System.out.println("While in close contact grab the {}'s wallet." + target);
    }
}
