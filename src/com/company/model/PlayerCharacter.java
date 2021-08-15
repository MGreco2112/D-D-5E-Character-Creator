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

        String weaponSelection = scanner.nextLine();

        readyWeapon((Weapon) gear.get(Integer.parseInt(weaponSelection) - 1));

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

    public static PlayerCharacter createPlayerCharacter() {
        String[] abilities = {"Strength", "Dexterity", "Constitution", "Intelligence", "Wisdom", "Charisma"};
        List<Integer> unselectedStats = new ArrayList<>();
        Map<String, Integer> selectedStats = new HashMap<>();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a character name: ");

        String name = scanner.nextLine();

        System.out.println("Enter a Class: ");

        String playerClass = scanner.nextLine();

        System.out.println("Enter your starting Level Number: ");

        String startingLevel = scanner.nextLine();

        String decision;


        do {

            System.out.println("These are your character's Ability Scores: ");

            while (unselectedStats.size() < 6) {
                unselectedStats.add(Die.rollStats());
            }

            for (int i = 0; i < unselectedStats.size(); i++) {
                System.out.println((i + 1) + ") " + unselectedStats.get(i));
            }

            System.out.println("Would you like to roll again?\n(y)es or (n)o");

            decision = scanner.nextLine();

            if (decision.toLowerCase(Locale.ROOT).equals("y")) {
                unselectedStats.clear();
            }

        } while (decision.toLowerCase(Locale.ROOT).equals("y"));


        while (selectedStats.size() < 6) {
            System.out.println("Which Stat Value would you like to assign to which Stat?");

            for (int i = 0; i < unselectedStats.size(); i++) {
                System.out.println((i+1) + ") " + unselectedStats.get(i));
            }

            for (int i = 0; i < abilities.length; i++) {
                if (abilities[i] != null) {
                    System.out.println((i + 1) + ") " + abilities[i]);
                }
            }

            System.out.println("Enter the number next to the stat total: ");

            String statSel = scanner.nextLine();

            System.out.println("And enter the number next to the Ability Name you want to assign the value to: ");

            String abilitySel = scanner.nextLine();

            selectedStats.put(abilities[Integer.parseInt(abilitySel) - 1],
                    unselectedStats.get(Integer.parseInt(statSel) - 1));

            unselectedStats.remove(Integer.parseInt(statSel) - 1);
            abilities[Integer.parseInt(abilitySel) - 1] = null;

        }
        System.out.println("These are your character's ability scores:");

        for (String abilityScore : selectedStats.keySet()) {
            System.out.println(abilityScore + ": " + selectedStats.get(abilityScore));
        }

        System.out.println("Enter starting Hit Point total: ");

        String hitPoints = scanner.nextLine();

        System.out.println("Enter your Armor Class: ");

        String armorClass = scanner.nextLine();

        System.out.println("Enter your Speed in feet per action: ");

        String speed = scanner.nextLine();

        System.out.println("Is your character a spellcaster?\n(y)es\n(n)o");

        String casterChoice = scanner.nextLine();
        boolean isArcane = false;
        boolean isDivine = false;
        boolean isRogue = false;

        if (casterChoice.toLowerCase(Locale.ROOT).equals("y")) {
            String choice = "";

            do {
                System.out.println("Is your character an Arcane caster or a Divine caster?\n1) Arcane\n2) Divine");

                choice = scanner.nextLine();
            } while (!choice.equals("1") && !choice.equals("2"));

            switch (Integer.parseInt(choice)) {
                case 1 :
                    System.out.println("Your character is an Arcane Caster");
                    isArcane = true;
                    break;

                case 2 :
                    System.out.println("Your character is a Divine Caster");
                    isDivine = true;
                    break;

                default :
                    System.out.println("Invalid entry");
            }


        }
        System.out.println("Is your character a Rogue?\n(y)es\n(n)o");

        String thiefChoice = scanner.nextLine();

        if (thiefChoice.toLowerCase(Locale.ROOT).equals("y")) {
            System.out.println("Your character is a Rogue");
            isRogue = true;
        } else {
            System.out.println("Your character is not a Rogue");
        }

        System.out.println("Enter your Alignment: ");

        String alignment = scanner.nextLine();


        return new PlayerCharacter(name, playerClass, Integer.parseInt(startingLevel),
                selectedStats.get("Strength"),
                selectedStats.get("Dexterity"),
                selectedStats.get("Constitution"),
                selectedStats.get("Intelligence")
                , selectedStats.get("Wisdom"), selectedStats.get("Charisma"), Integer.parseInt(hitPoints),
                Integer.parseInt(armorClass), Integer.parseInt(speed), 2,
                0, isArcane, isDivine, isRogue,
                alignment);

    }

    public String toString() {
        return name + ":\n" + classLevel + " Level: " + level + "\nCurrent Hit Points: " + hitPoints + "\nMax Hit " +
                "Points: " + maxHitPoints + "\nAbility " +
                "Scores: " + stats +
                "\nEquipment: " + gear +
                "\nSpells: " + spells;
    }
}
