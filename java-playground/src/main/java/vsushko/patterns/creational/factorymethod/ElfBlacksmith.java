package vsushko.patterns.creational.factorymethod;

/**
 * Concrete subclass for creating new objects
 */
public class ElfBlacksmith implements Blacksmith {
    @Override
    public Weapon manufactureWeapon(WeaponType weaponType) {
        return new ElfWeapon(weaponType);
    }
}
