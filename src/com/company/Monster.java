package com.company;

import java.util.ArrayList;

// TODO add unique String description field in constructor
public class Monster {
    public Die d20 = new Die(20,20);
    public int hitPoints;
    public int attackBonus;
    public int armorClass;
    public boolean isDead;
    public String name;
    public Weapon weapon;
    public ArrayList<Item> treasure = new ArrayList<>();

    public Monster(String name, int hitPoints, int armorClass, int attackBonus, Weapon attack) {
        this.name = name;
        this.hitPoints = hitPoints;
        this.attackBonus = attackBonus;
        this.armorClass = armorClass;
        this.weapon = attack;
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
        int flatRoll = d20.faceUpValue;
        if (attackRoll >= target.armorClass && flatRoll != 1 || flatRoll == 20) {
            System.out.println(name + " hits " + target.name + " with " + attack.name + "!");
            int damageRoll = (weapon.damageCode.roll() * weapon.numberOfDice);
            target.hitPoints -= damageRoll;
            System.out.println(target.name + " takes " + damageRoll + " points of damage!");
            target.checkStatus();
        } else if (flatRoll == 1) {
            System.out.println(name + " missed! Critical Fail!");
        } else {
            System.out.println(attack.name + " missed!");
        }
    }

}
