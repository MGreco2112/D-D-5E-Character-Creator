package com.company.model;

public class Item {
    String name;
    String properties;
    int weight;
    boolean isWeapon;

    public Item(String name, String properties, int weight) {
        this.name = name;
        this.properties = properties;
        this.weight = weight;
        this.isWeapon = false;
    }

    public String toString() {
        return name + ": " + properties + ", weight: " + weight + " lbs.";
    }



}
