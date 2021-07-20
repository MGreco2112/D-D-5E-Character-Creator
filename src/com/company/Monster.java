package com.company;

import java.util.ArrayList;

public class Monster {
    public Die d20 = new Die(20,20);
    public int hitPoints;
    public int attackBonus;
    public int armorClass;
    public boolean isDead;
    public String name;
    public Weapon attack;
    public ArrayList<Item> treasure = new ArrayList<>();

    public Monster(String name, int hitPoints, int armorClass, int attackBonus, Weapon attack) {
        this.name = name;
        this.hitPoints = hitPoints;
        this.attackBonus = attackBonus;
        this.armorClass = armorClass;
        this.attack = attack;
    }

    public void addTreasure(Item treasure) {
        this.treasure.add(treasure);
        System.out.println(treasure + " has been added.");
    }


    public void checkStatus() {
        isDead = hitPoints <= 0;
        if (isDead) {
            System.out.println(name + " has been defeated!");
        }
    }


    public void attack(Weapon attack, Character target) {
        int attackRoll = d20.roll() + attackBonus;
        if (attackRoll >= target.armorClass) {
            System.out.println(name + " hits " + target.name + " with " + attack.name + "!");
            int damageRoll = attack.damageCode.roll();
            target.hitPoints -= damageRoll;
            System.out.println(target.name + " takes " + damageRoll + " points of damage!");
            target.checkStatus();
        } else {
            System.out.println(attack.name + " missed!");
        }
    }

}
