package vsushko.patterns.behavioral.state;

/**
 * Peaceful state.
 */
public class PeacefulState implements State {
    private Mammoth mammoth;

    public PeacefulState(Mammoth mammoth) {
        this.mammoth = mammoth;
    }

    @Override
    public void observe() {
        System.out.println("{} is calm and peaceful." + mammoth);
    }

    @Override
    public void onEnterState() {
        System.out.println("{} calms down." + mammoth);
    }
}
