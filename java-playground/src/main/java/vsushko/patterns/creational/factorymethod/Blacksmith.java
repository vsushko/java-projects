package vsushko.patterns.creational.factorymethod;

/**
 * The interface containing method for producing objects
 */
public interface Blacksmith {
    Weapon manufactureWeapon(WeaponType weaponType);
}
