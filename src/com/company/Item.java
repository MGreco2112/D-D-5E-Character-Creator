package com.company;

public class Item {
    String name;
    String properties;
    int weight;
    boolean isWeapon;
    Die damageCode;

    public Item(String name, String properties, int weight) {
        this.name = name;
        this.properties = properties;
        this.weight = weight;
        this.isWeapon = false;
    }

    public Item(String name, String properties, int weight, Die damageCode) {
        this.name = name;
        this.properties = properties;
        this.weight = weight;
        this.isWeapon = true;
        this.damageCode = damageCode;
    }

}
