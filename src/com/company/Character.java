package com.company;

import java.util.ArrayList;

public class Character {
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
    ArrayList<String> spells = new ArrayList<>();
    ArrayList<String> gear = new ArrayList<>();
    ArrayList<Integer> stats = new ArrayList<>();

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

    public String addSpell(String spell) {
        spells.add(spell);
        return spell + " has been added.";
    }

    public String addEquipment(String equipment) {
        gear.add(equipment);
        return equipment + " has been added";
    }

    public String toString() {
        return name + " " + classLevel + " " + level + " " + stats + " " + gear + " " + spells;
    }
}
