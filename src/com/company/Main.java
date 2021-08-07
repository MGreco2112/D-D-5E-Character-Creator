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

        } while (!decision.toLowerCase(Locale.ROOT).equals("y"));

        //Add ability to set hashMap keys as Ability Score Names and set values as chosen by the player

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

        for (String abilityScore : selectedStats.keySet()) {
            System.out.println("These are your character's ability scores:\n" + abilityScore + ": " + selectedStats.get(abilityScore));
        }






        PlayerCharacter currentPlayer = new PlayerCharacter(name, playerClass, Integer.parseInt(startingLevel),
                selectedStats.get("Strength"),
                selectedStats.get("Dexterity"),
                selectedStats.get("Constitution"),
                selectedStats.get("Intelligence")
        , selectedStats.get("Wisdom"), selectedStats.get("Charisma"), 8, 12, 30, 2, 0, true, false, true,
                "Lawful Neutral");




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
