package vsushko.patterns.creational.prototype;

public interface HeroFactory {
    Mage createMage();
    Warlord createWarlord();
    Beast createBeast();
}
