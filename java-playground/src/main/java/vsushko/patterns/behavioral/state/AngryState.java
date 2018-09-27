package vsushko.patterns.behavioral.state;

/**
 * Angry state.
 */
public class AngryState implements State {
    private Mammoth mammoth;

    public AngryState(Mammoth mammoth) {
        this.mammoth = mammoth;
    }

    @Override
    public void observe() {
        System.out.println("{} is furious!" + mammoth);
    }

    @Override
    public void onEnterState() {
        System.out.println("{} gets angry!" + mammoth);
    }
}
