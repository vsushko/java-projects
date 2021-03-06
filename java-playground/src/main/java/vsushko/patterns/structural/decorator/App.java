package vsushko.patterns.structural.decorator;

/**
 * The Decorator pattern is a more flexible alternative to subclassing. The Decorator class
 * implements the same interface as the target and uses aggregation to "decorate" calls to the
 * target. Using the Decorator pattern it is possible to change the behavior of the class during
 * runtime.
 * <p>
 * In this example we show how the simple {@link SimpleTroll} first attacks and then flees the battle.
 * Then we decorate the {@link SimpleTroll} with a {@link ClubbedTroll} and perform the attack again. You
 * can see how the behavior changes after the decoration.
 */
public class App {
    /**
     * Program entry point
     *
     * @param args command line args
     */
    public static void main(String[] args) {

        // simple troll
        System.out.println("A simple looking troll approaches.");
        Troll troll = new SimpleTroll();
        troll.attack();
        troll.fleeBattle();
        System.out.println("Simple troll power {}.\n" + troll.getAttackPower());

        // change the behavior of the simple troll by adding a decorator
        System.out.println("A troll with huge club surprises you.");
        troll = new ClubbedTroll(troll);
        troll.attack();
        troll.fleeBattle();
        System.out.println("Clubbed troll power {}.\n" + troll.getAttackPower());
    }
}
