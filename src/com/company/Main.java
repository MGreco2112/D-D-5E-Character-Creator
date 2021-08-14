package com.company;


import com.company.model.*;

import java.lang.Character;
import java.util.*;

public class Main {

    // TODO add classes Trap, Puzzle, Created Items, Created Monsters, Created Spells
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Die dPercent = new Die(100, 100);
        Die d20 = new Die(20, 20);
        Die d12 = new Die(12,12);
        Die d10 = new Die(10,10);
        Die d8 = new Die(8,8);
        Die d6 = new Die(6,6);
        Die d4 = new Die(4,4);
        Die d2 = new Die(2,2);
        String[] abilities = {"Strength", "Dexterity", "Constitution", "Intelligence", "Wisdom", "Charisma"};
        List<Integer> unselectedStats = new ArrayList<>();
        Map<String, Integer> selectedStats = new HashMap<>();


        Gear clothes = new Gear("Common Clothes", "Tunic; gray", 3);
        Spell mage_hand = new Spell("Mage Hand", 0, "Conjuration", 1, 30, "V, S", 60, 0);
        Spell magic_missile = new Spell("Magic Missile", 1, "Evocation", 1, 120, "V, S", 0, 4, d4, 1);


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
        boolean isThief = false;

        if (casterChoice.toLowerCase(Locale.ROOT).equals("y")) {
            String choice = "";

            do {
                System.out.println("Is your character an Arcane caster or a Divine caster?\n1) Arcane\n2) Divine");

                choice = scanner.nextLine();
            } while (!choice.equals("1") || !choice.equals("2"));

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

            System.out.println("Is your character a Thief?\n(y)es\n(n)o");

            String thiefChoice = scanner.nextLine();

            if (thiefChoice.toLowerCase(Locale.ROOT).equals("y")) {

            }

        }

        System.out.println("Enter your Alignment: ");

        String alignment = scanner.nextLine();


        PlayerCharacter currentPlayer = new PlayerCharacter(name, playerClass, Integer.parseInt(startingLevel),
                selectedStats.get("Strength"),
                selectedStats.get("Dexterity"),
                selectedStats.get("Constitution"),
                selectedStats.get("Intelligence")
        , selectedStats.get("Wisdom"), selectedStats.get("Charisma"), Integer.parseInt(hitPoints),
                Integer.parseInt(armorClass), Integer.parseInt(speed), 2,
                0, isArcane, isDivine, true,
                alignment);




        currentPlayer.addEquipment(WeaponCollection.dagger());
        currentPlayer.addEquipment(clothes);
        currentPlayer.readyWeapon((Weapon) currentPlayer.gear.get(0));
        currentPlayer.addProficiency("Dagger");
        currentPlayer.addProficiency("Mace");
        currentPlayer.addSpell(mage_hand);
        currentPlayer.addSpell(magic_missile);

        System.out.println(currentPlayer);

        Monster goblin = new Monster("Goblin", "Ugly, Green, Hooked nose, Angry", 5, 12, 2, WeaponCollection.club()
        , 500);






        Room room1 = new Room("Entry Way", "A 20' x 20' cave entrance filled with dripping stalactites. The Way out " +
                "is to the South, a wooden door stands to the North.", 2,
                false,false,
                false, false, 14);
        Room room2 = new Room("Cave", "A 10' by 50' natural hallway sunken deep into the mountain. A wooden door is " +
                "to the South.", 1, true, false,
                false, false, 0);

        room1.addItem(WeaponCollection.mace());


        Dungeon testDungeon = new Dungeon("Test Dungeon", 2);
        testDungeon.addRoom(room1);
        testDungeon.addRoom(room2);

        Door entranceDoor = new Door("Cave", "Gaping hole in the side of a mountain", 0, false, false, true, room1,
                room1);

        Door door1 = new Door("Wood", "Between the cave mouth and the back passage", 2, false, false, false, room1,
                room2);

        Chest testChest = new Chest("Test Chest", 1, false);

        testChest.addItem(WeaponCollection.shortsword());
        room1.addItem(testChest);

        room1.addDoor(entranceDoor);
        room1.addDoor(door1);
        room2.addDoor(door1);
        room2.addMonster(goblin);


        testDungeon.addCharacter(currentPlayer);

        testDungeon.startDungeon();


    }
}
