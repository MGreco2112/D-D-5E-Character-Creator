package com.company;

import java.util.ArrayList;

public class Character {
    public Die d20 = new Die(20,20);
    public int level;
    public int str;
    public int dex;
    public int con;
    public int intel;
    public int wis;
    public int cha;
    public int hitPoints;
    public int armorClass;
    public int speed;
    public int profBonus;
    public int gold;
    public boolean isArcane;
    public boolean isDivine;
    public boolean isRogue;
    public String alignment;
    public String name;
    public String classLevel;
    ArrayList<Spell> spells = new ArrayList<>();
    ArrayList<Item> gear = new ArrayList<>();
    ArrayList<Integer> stats = new ArrayList<>();
    ArrayList<String> proficiencies = new ArrayList<>();

    public Character(String name, String classLevel, int level, int str, int dex, int con, int intel, int wis, int cha,
                     int hitPoints,
                     int armorClass, int speed, int profBonus, int gold, boolean isArcane, boolean isDivine,
                     boolean isRogue,
                     String alignment) {
        this.name = name;
        this.classLevel = classLevel;
        this.level = level;
        this.str = str;
        stats.add(str);
        this.dex = dex;
        stats.add(dex);
        this.con = con;
        stats.add(con);
        this.intel = intel;
        stats.add(intel);
        this.wis = wis;
        stats.add(wis);
        this.cha = cha;
        stats.add(cha);
        this.hitPoints = hitPoints;
        this.armorClass = armorClass;
        this.speed = speed;
        this.gold = gold;
        this.profBonus = profBonus;
        this.isArcane = isArcane;
        this.isDivine = isDivine;
        this.isRogue = isRogue;
        this.alignment = alignment;
    }

    public String addSpell(Spell spell) {
        if (isArcane || isDivine) {
            spells.add(spell);
            return spell + " has been added.";
        } else {
            return name + " is not a caster.";
        }
    }

    public String addEquipment(Item equipment) {
        gear.add(equipment);
        return equipment + " has been added";
    }

    public String addProficiency(String proficiency) {
        proficiencies.add(proficiency);
        return proficiency + " has been added";
    }

    // Modify when creating Monster Class to add Target to Parameters
    public void attack(Weapon weapon) {
        if (gear.contains(weapon)) {
            int attackRoll = d20.roll();
                if (proficiencies.contains(weapon.name)) {
                    attackRoll += profBonus;
                }
            System.out.println(attackRoll);
        } else {
            System.out.println("Weapon is not equipped!");
        }

    }

    public void reloadWeapon(Weapon weapon, Gear ammo, int amount) {
        if (weapon.takesAmmo) {
            ammo.qty -= amount;
            weapon.ammo += amount;
            System.out.println(weapon.name + " now has " + weapon.ammo + " ammunition.");
        } else {
            System.out.println(weapon.name + " does not take ammunition.");
        }
    }

    public String toString() {
        return name + ":\n" + classLevel + " Level: " + level + "\nAbility Scores: " + stats + "\nEquipment: " + gear +
                "\nSpells: " + spells;
    }
}
