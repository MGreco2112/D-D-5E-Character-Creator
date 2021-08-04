package com.company.model;

import java.util.*;

// TODO add skills section to work with prof bonus and ability modifiers, add spell system mechanics

/**
 * Java class which models a DnD character.
 */
public class PlayerCharacter {
    public Die d20 = new Die(20,20);
    public Room currentRoom;
    private final Scanner scanner = new Scanner(System.in);

    public int level;
    public int str;
    public int dex;
    public int con;
    public int intel;
    public int wis;
    public int cha;
    public int hitPoints;
    public int maxHitPoints;
    public int armorClass;
    public int speed;
    public int profBonus;
    public int gold;
    public int experiencePoints = 0; // TODO Modify this into constructor if character is not starting with zero
    public boolean isArcane;
    public boolean isDivine;
    public boolean isRogue;
    public boolean isDead;
    public String alignment;
    public String name;
    public String classLevel;
    public Weapon readiedWeapon;

    public List<Spell> spells = new ArrayList<>();
    public List<Item> gear = new ArrayList<>();
    public List<Integer> stats = new ArrayList<>();
    public List<String> proficiencies = new ArrayList<>();
    public List<String> proficientSkills = new ArrayList<>();

    private Map<Integer,Integer> abilityModifierMap = new HashMap<>();


    public PlayerCharacter(String name, String classLevel, int level, int str, int dex, int con, int intel, int wis, int cha,
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
        this.maxHitPoints = hitPoints + abilityMod(this.con);
        this.armorClass = armorClass;
        this.speed = speed;
        this.gold = gold;
        this.profBonus = profBonus;
        this.isArcane = isArcane;
        this.isDivine = isDivine;
        this.isRogue = isRogue;
        this.alignment = alignment;
        abilityModifierMap = populateAbilityModifiers();
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

    // TODO Change skill from String to actual Object with name and root Ability
    public void addSkill(String skill) {
        proficientSkills.add(skill);
    }

    public int abilityCheck(String ability) {
        int abilityCheck = d20.roll();
        int abilityScore = 0;

        switch (ability){
            case "str":
            case "Strength":
            case "strength":
                abilityScore = str;
                break;
            case "dex":
            case "Dexterity":
            case "dexterity":
                abilityScore = dex;
                break;
            case "con":
            case "Constitution":
            case "constitution":
                abilityScore = con;
                break;
            case "int":
            case "Intelligence":
            case "intelligence":
                abilityScore = intel;
                break;
            case "wis":
            case "Wisdom":
            case "wisdom":
                abilityScore = wis;
                break;
            case "cha":
            case "Charisma":
            case "charisma":
                abilityScore = cha;
                break;

        }

        abilityCheck += abilityMod(abilityScore);

        return abilityCheck;
    }

    /*
    Replaced conditional structure with a map for better readability
     */

    /**
     * Method to pre-populate modifiers associated with each ability score value from 1 - 30
     * @return map of modifiers
     */
    private Map<Integer,Integer> populateAbilityModifiers(){
        Map<Integer,Integer> abilityMap = new HashMap<>();
        int change = -5;
        int step = 0;
        for(int i = 1; i <= 30; i++){
            if(step>1){
                change++;
                step = 0;
            }
            abilityMap.put(i,change);
            step++;
        }
        return abilityMap;
    }

    private int abilityMod(int abilityScore) {
        Integer abilityModifier = abilityModifierMap.get(abilityScore);

        if(abilityModifier==null){
            System.out.println("Invalid Ability Modifier!");
            return 0;
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
                    System.out.println(name + " hits with " + weapon.name + "!");
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

    public String recoverHealth(int health) {
        hitPoints += health;

        if (hitPoints >= maxHitPoints) {
            hitPoints = maxHitPoints;
        }

        return name + " regains " + health + " Hit Points.\n" + name + " has " + hitPoints + " total Hit Points";
    }

    public void changeActiveWeapon() {
        System.out.println("Which Weapon shall " + name + " wield?");
        for (int i = 0; i < gear.size(); i++) {
            if (gear.get(i).isWeapon) {
                System.out.println((i+1) + ") " + gear.get(i).name);
            }
        }

        int weaponSelection = scanner.nextInt() - 1;

        readyWeapon((Weapon) gear.get(weaponSelection));

    }

    // TODO Finish effect of casting Spell
    public void castSpell(Spell spell, Monster target) {
        if (spell.damageCode != null) {
            int spellRoll = spell.effect();

            if (isArcane) {
                spellRoll += abilityMod(intel);
            } else if (isDivine) {
                spellRoll += abilityMod(wis);
            }

            System.out.println(name + " casts " + spell.name + " at " + target.name + "!\nRoll: " + spellRoll);

            if (spellRoll >= target.armorClass) {
                int damageRoll = (spell.damageCode.roll() * spell.numberOfDice) + spell.rollBonus;

                System.out.println("Hit!\n" + spell.name + " deals " + damageRoll + " points of damage to " + target.name +
                        "!");
                target.hitPoints -= damageRoll;
            } else {
                System.out.println("The spell missed!");
            }

        } else {
            System.out.println("Non combative spell!");
        }
    }

    public String gainExperience(Monster enemy) {
        experiencePoints += enemy.experiencePoints;

        return name + " gains " + enemy.experiencePoints + " experience points!";
    }

    public void checkStatus() {
        isDead = hitPoints <= 0;
        if (isDead) {
            System.out.println(name + " has died. Game over");
            currentRoom.dungeon.endGame();
        }
    }

    public void characterMenu() {

        String choice = "";

        while (!choice.equals("e")) {

            System.out.println("Select an option:\n(c)heck stats\n(u)se item\n(s)wap active weapon\n(e)xit");

            choice = scanner.nextLine();

            switch (choice.toLowerCase(Locale.ROOT)) {
                case "c":
                    System.out.println(this);
                    break;

                case "u":

                    for (int i = 0; i < gear.size(); i++) {
                        System.out.println((i + 1) + ") " + gear.get(i).name);
                    }

                    System.out.println("Usable items aren't coded just yet, thanks for testing!");

                    break;

                case "s":
                    changeActiveWeapon();

                    break;

                case "e":

                    break;

                default:
                    System.out.println("Invalid entry, try again.");
            }
        }


    }

    public String toString() {
        return name + ":\n" + classLevel + " Level: " + level + "\nCurrent Hit Points:" + hitPoints + "\nAbility " +
                "Scores: " + stats +
                "\nEquipment: " + gear +
                "\nSpells: " + spells;
    }
}
