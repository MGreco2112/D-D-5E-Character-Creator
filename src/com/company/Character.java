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
    public boolean isDead;
    public String alignment;
    public String name;
    public String classLevel;
    public Weapon readiedWeapon;
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

    public void readyWeapon(Weapon weapon) {
        readiedWeapon = weapon;
    }

    public String addProficiency(String proficiency) {
        proficiencies.add(proficiency);
        return proficiency + " has been added";
    }

    public int abilityCheck(String ability) {
        int abilityCheck = d20.roll();
        int abilityScore = 0;

        if (ability.equals("str") || ability.equals("Strength") || ability.equals("strength")) {
            abilityScore = str;
        } else if (ability.equals("dex") || ability.equals("Dexterity") || ability.equals("dexterity")) {
            abilityScore = dex;
        } else if (ability.equals("con") || ability.equals("Constitution") || ability.equals("constitution")) {
            abilityScore = con;
        } else if (ability.equals("int") || ability.equals("Intelligence") || ability.equals("intelligence")) {
            abilityScore = intel;
        } else if (ability.equals("wis") || ability.equals("Wisdom") || ability.equals("wisdom")) {
            abilityScore = wis;
        } else if (ability.equals("cha") || ability.equals("Charisma") || ability.equals("charisma")) {
            abilityScore = cha;
        }

        abilityCheck += abilityMod(abilityScore);

        return abilityCheck;
    }

    private int abilityMod(int abilityScore) {
        int abilityModifier = 0;

        if (abilityScore == 1) {
            abilityModifier -= 5;
        } else if (abilityScore <= 3) {
            abilityModifier -=4;
        } else if (abilityScore <= 5) {
            abilityModifier -= 3;
        } else if (abilityScore <= 7) {
            abilityModifier -= 2;
        } else if (abilityScore <= 9) {
            abilityModifier -= 1;
        } else if (abilityScore <= 11) {
            abilityModifier += 0;
        } else if (abilityScore <= 13) {
            abilityModifier += 1;
        } else if (abilityScore <= 15) {
            abilityModifier += 2;
        } else if (abilityScore <= 17) {
            abilityModifier += 3;
        } else if (abilityScore <= 19) {
            abilityModifier += 4;
        } else if (abilityScore <= 21) {
            abilityModifier += 5;
        } else if (abilityScore <= 23) {
            abilityModifier += 6;
        } else if (abilityScore <= 25) {
            abilityModifier += 7;
        } else if (abilityScore <= 27) {
            abilityModifier += 8;
        } else if (abilityScore <= 29) {
            abilityModifier += 9;
        } else if (abilityScore == 30) {
            abilityModifier += 10;
        }

        return abilityModifier;
    }


    public void attack(Weapon weapon, Monster enemy) {
        if (gear.contains(weapon)) {
            int attackRoll = d20.roll();
            int flatRoll = d20.faceUpValue;
                if (proficiencies.contains(weapon.name)) {
                    attackRoll += profBonus;
                    System.out.println(attackRoll + " is rolled against " + enemy.name + "'s armor class.");
                }
                if (attackRoll >= enemy.armorClass && flatRoll != 1 || flatRoll == 20) {
                    System.out.println(name + " hits with " + weapon.name + " !");
                    int damageRoll = (weapon.damageCode.roll() * weapon.numberOfDice);
                    enemy.hitPoints -= damageRoll;
                    System.out.println(damageRoll + " points of damage dealt to " + enemy.name);
                    enemy.checkStatus();
                } else if (flatRoll == 1) {
                    System.out.println("Critical Failure! Miss!");
                } else {
                    System.out.println("The attack did not hit!");
                }

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

    public void checkStatus() {
        isDead = hitPoints <= 0;
        if (isDead) {
            System.out.println(name + " has died. Game over");
        }
    }

    public String toString() {
        return name + ":\n" + classLevel + " Level: " + level + "\nAbility Scores: " + stats + "\nEquipment: " + gear +
                "\nSpells: " + spells;
    }
}
