package com.company.model;

public class Gear extends Item{
    public int qty;

    public Gear(String name, String properties, int weight, int qty) {
        super(name, properties, weight);
        this.qty = qty;
        this.isWeapon = false;
    }

    public Gear(String name, String properties, int weight) {
        super(name, properties, weight);
        this.isWeapon = false;
    }
}
