package com.company.model;


public class Weapon extends Item {
    public Die damageCode;
    public int numberOfDice;
    public boolean takesAmmo;
    public int ammo = 0;

    public Weapon(String name, boolean takesAmmo, String properties, int weight, int numberOfDice, Die damageCode) {
        super(name, properties, weight);
        this.takesAmmo = takesAmmo;
        this.isWeapon = true;
        this.numberOfDice = numberOfDice;
        this.damageCode = damageCode;
    }


    public String toString() {
        return name + ", Properties:" + properties + ", weight: " + weight + " lbs. Damage: " + numberOfDice + damageCode;
    }
}
