package com.company;

public class Weapon extends Item{

    public Weapon(String name, String properties, int weight, Die damageCode) {
        super(name, properties, weight, true, damageCode);
    }


}
