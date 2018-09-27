package vsushko.patterns.creational.prototype;

public class HeroFactoryImpl implements HeroFactory {
    private Mage mage;
    private Warlord warlord;
    private Beast beast;

    public HeroFactoryImpl(Mage mage, Warlord warlord, Beast beast) {
        this.mage = mage;
        this.warlord = warlord;
        this.beast = beast;
    }

    public Mage createMage() {
        try {
            return mage.copy();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public Warlord createWarlord() {
        try {
            return warlord.copy();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public Beast createBeast() {
        try {
            return beast.copy();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
