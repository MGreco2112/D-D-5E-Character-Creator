package com.company;

public class Weapon extends Item{
    public Die damageCode;
    public int numberOfDice;

    public Weapon(String name, String properties, int weight, int numberOfDice, Die damageCode) {
        super(name, properties, weight);
        this.isWeapon = true;
        this.numberOfDice = numberOfDice;
        this.damageCode = damageCode;
    }


}
