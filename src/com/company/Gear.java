package com.company;

public class Gear extends Item{

    public Gear(String name, String properties, int weight) {
        super(name, properties, weight);
        this.isWeapon = false;
    }
}
