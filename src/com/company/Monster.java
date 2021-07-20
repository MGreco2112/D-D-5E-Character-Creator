package com.company;

import java.util.ArrayList;

public class Monster {
    public Die d20 = new Die(20,20);
    public int hitPoints;
    public int attackBonus;
    public String name;
    public Weapon attack;
    public ArrayList<Item> treasure = new ArrayList<>();
    public ArrayList<Weapon> weapons = new ArrayList<>();

    public Monster(String name, int hitPoints, int attackBonus, Weapon attack) {
        this.name = name;
        this.hitPoints = hitPoints;
        this.attackBonus = attackBonus;
        this.attack = attack;
    }

    public void addTreasure(Item treasure) {
        this.treasure.add(treasure);
        System.out.println(treasure + " has been added.");
    }

    public void addWeapon(Weapon attack) {
        weapons.add(attack);
        System.out.println(attack + " has been added.");
    }

    // need to add target parameter and target AC
    public void attack(Weapon attack) {
        if (weapons.contains(attack)) {
            int attackRoll = d20.roll() + attackBonus;
            System.out.println(attackRoll);
        } else {
            System.out.println("Weapon is not equipped.");
        }
    }

}
