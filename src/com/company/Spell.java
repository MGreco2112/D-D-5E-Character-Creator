package com.company;

public class Spell {
    public String name;
    public String school;
    public String components;
    public Die damageCode;
    public int rollBonus;
    public int numberOfDice;
    public int level;
    public int castingTime;
    public int range;
    public int duration;

    public Spell(String name, int level, String school, int castingTime, int range, String components,
                 int duration, int rollBonus) {
        this.name = name;
        this.level = level;
        this.school = school;
        this.castingTime = castingTime;
        this.range = range;
        this.components = components;
        this.duration = duration;
        this.damageCode = null;
        this.numberOfDice = 0;
        this.rollBonus = rollBonus;
    }

    public Spell(String name, int level, String school, int castingTime, int range, String components, int duration,
                 int numberOfDice, Die damageCode, int rollBonus) {
        this.name = name;
        this.level = level;
        this.school = school;
        this.castingTime = castingTime;
        this.range = range;
        this.components = components;
        this.duration = duration;
        this.numberOfDice = numberOfDice;
        this.damageCode = damageCode;
        this.rollBonus = rollBonus;
    }

    public int effect() {
        Die d20 = new Die(20,20);

        return d20.roll();
    }

    public String toString() {
        return name + ((damageCode == null) ? " Non-combative Spell" :" Combative Spell: " + numberOfDice + damageCode);
    }
}
