package com.company.model;

import com.company.model.Die;
import com.company.services.DiceService;

public class Spell {
    public String name;
    public String school;
    public String components;
    public DiceType damageCode; //Modified to Enum, see DiceType for details
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
                 int numberOfDice, DiceType damageCode, int rollBonus) {
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

    //Removed new instance of d20 and utilized new Dice Service.
    public int effect() {
        return DiceService.roll_20();
    }

    //Moved damage logic here.
    public int getDamage(){
        return (DiceService.rollType(this.damageCode) * this.numberOfDice) + this.rollBonus;
    }

    public String toString() {
        return name + ((damageCode == null) ? " Non-combative Spell" :" Combative Spell: " + numberOfDice + damageCode);
    }
}
