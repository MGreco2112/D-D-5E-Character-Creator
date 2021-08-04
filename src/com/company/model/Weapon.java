package com.company.model;

import com.company.model.Die;
import com.company.model.Item;
import com.company.services.DiceService;

public class Weapon extends Item {
    public DiceType damageCode;
    public int numberOfDice;
    public boolean takesAmmo;
    public int ammo = 0;

    public Weapon(String name, boolean takesAmmo, String properties, int weight, int numberOfDice, DiceType damageCode) {
        super(name, properties, weight);
        this.takesAmmo = takesAmmo;
        this.isWeapon = true;
        this.numberOfDice = numberOfDice;
        this.damageCode = damageCode;
    }


    public String toString() {
        return name + ", Properties:" + properties + ", weight: " + weight + " lbs. Damage: " + numberOfDice + damageCode;
    }

    //Moved logic for calculating weapon damage (without modifiers)
    public int getDamage(){
        return DiceService.rollType(this.damageCode) * numberOfDice;
    }
}
