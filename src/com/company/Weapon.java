package com.company;

public class Weapon extends Item{
    public Die damageCode;

    public Weapon(String name, String properties, int weight, Die damageCode) {
        super(name, properties, weight);
        this.isWeapon = true;
        this.damageCode = damageCode;
    }


}
