package com.company;

import java.util.Locale;
import java.util.Scanner;

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


        Gear clothes = new Gear("Common Clothes", "Tunic; gray", 3);
        Spell mage_hand = new Spell("Mage Hand", 0, "Conjuration", 1, 30, "V, S", 60, 0);
        Spell magic_missile = new Spell("Magic Missile", 1, "Evocation", 1, 120, "V, S", 0, 4, d4, 1);


        Character winter = new Character("Winter", "Bard", 1, Die.rollStats(), Die.rollStats(), Die.rollStats(),
                Die.rollStats()
        , Die.rollStats(), Die.rollStats(), 8, 12, 30, 2, 0, true, false, true,
                "Lawful Neutral");




        winter.addEquipment(WeaponCollection.dagger());
        winter.addEquipment(clothes);
        winter.readyWeapon((Weapon) winter.gear.get(0));
        winter.addProficiency("Dagger");
        winter.addProficiency("Mace");
        winter.addSpell(mage_hand);
        winter.addSpell(magic_missile);

        System.out.println(winter);

        Monster goblin = new Monster("Goblin", "Ugly, Green, Hooked nose, Angry", 5, 12, 2, WeaponCollection.club()
        , 500);






        Room room1 = new Room("Entry Way", "A 20' x 20' cave entrance filled with dripping stalactites. The Way out " +
                "is to the South, a wooden door stands to the North.", 2,
                false,false,
                false, false);
        Room room2 = new Room("Cave", "A 10' by 50' natural hallway sunken deep into the mountain. A wooden door is " +
                "to the South.", 1, true, false,
                false, false);

        room1.addItem(WeaponCollection.mace());


        Dungeon testDungeon = new Dungeon("Test Dungeon", 2);
        testDungeon.addRoom(room1);
        testDungeon.addRoom(room2);

        Door entranceDoor = new Door("Cave", "Gaping hole in the side of a mountain", 0, false, false, true, room1,
                room1);

        Door door1 = new Door("Wood", "Between the cave mouth and the back passage", 2, false, false, false, room1,
                room2);

        room1.addDoor(entranceDoor);
        room1.addDoor(door1);
        room2.addDoor(door1);
        room2.addMonster(goblin);


        testDungeon.addCharacter(winter);

        testDungeon.startDungeon();

        String choice = "";

        System.out.println(testDungeon.currentRoom.description);

        // TODO remove all instances of testDungeon when moved into Dungeon class

        while (!choice.equals("o")) { //TODO While loop only for Main method use. Will remove when moved into Dungeon

            System.out.println("What will " + testDungeon.activeCharacter.name + " do?\n(l)ook\n(s)earch\n(o)pen door");

            choice = scanner.nextLine();

            switch (choice.toLowerCase(Locale.ROOT)) {
                case "l":
                    System.out.println(testDungeon.currentRoom.description);
                    break;

                case "s":
                    System.out.println(testDungeon.activeCharacter.name + " is searching...");
                    if (testDungeon.currentRoom.roomItems.size() != 0) {
                        int perceptionCheck = testDungeon.activeCharacter.abilityCheck("wis");
                        if (perceptionCheck >= 13) {
                            System.out.println(testDungeon.activeCharacter.name + " succeeds in finding items!");
                            System.out.println("Enter the number next to the item to pick it up");

                            for (int i = 0; i < testDungeon.currentRoom.roomItems.size(); i++) {
                                System.out.println((i + 1) + ") " + testDungeon.currentRoom.roomItems.get(i));
                            }
                            System.out.println("0) Take All");
                            int selectedItem = scanner.nextInt() - 1;

                            if (selectedItem != -1) {
                                if (testDungeon.currentRoom.roomItems.get(selectedItem) instanceof Chest) {
                                    ((Chest) testDungeon.currentRoom.roomItems.get(selectedItem)).openChest();
                                } else {
                                System.out.println(testDungeon.activeCharacter.name + " takes " + testDungeon.currentRoom.roomItems.get(selectedItem));
                                testDungeon.activeCharacter.addEquipment(testDungeon.currentRoom.roomItems.get(selectedItem));
                                testDungeon.currentRoom.roomItems.remove(selectedItem);
                                }

                            } else {
                                for (Item item : testDungeon.currentRoom.roomItems) {

                                    if (item instanceof Chest) {
                                        System.out.println("Chest cannot be taken");
                                    } else {
                                        testDungeon.activeCharacter.addEquipment(item);
                                        System.out.println(testDungeon.activeCharacter.name + " takes " + testDungeon.currentRoom.roomItems.get(testDungeon.currentRoom.roomItems.indexOf(item)));
                                        for (int i = 0; i < testDungeon.currentRoom.roomItems.size(); i++) {
                                            testDungeon.currentRoom.roomItems.remove(i);
                                            i--;
                                        }

                                    }
                                }

                                break;
                            }

                        } else {
                            System.out.println(testDungeon.activeCharacter.name + " finds nothing.");
                            break;
                        }
                    } else {
                        System.out.println(testDungeon.activeCharacter.name + " finds nothing");
                        break;
                    }

                    break;

                case "o":
                    System.out.println("Which door will " + testDungeon.activeCharacter.name + " attempt to open?");
                    int doorCount = 1;
                    for (Door door : testDungeon.currentRoom.doors) {
                        System.out.println(doorCount + ") " + door.type + " door, " + door.locationInRoom);
                        doorCount++;
                    }

                    int doorSelection = scanner.nextInt() - 1;

                    testDungeon.currentRoom.doors.get(doorSelection).open();
            }
        }


        while (testDungeon.currentRoom.activeMonster) {
            System.out.println("There are monsters in this room! Which will you fight?");
            for (int i = 0; i < testDungeon.currentRoom.roomMonsters.size(); i++) {
                System.out.println((i + 1) + ") " + testDungeon.currentRoom.roomMonsters.get(i).name);
            }
            System.out.println("Enter the number of the Monster to fight:");

            int monsterSelection = scanner.nextInt();

            if (monsterSelection > testDungeon.currentRoom.roomMonsters.size()) {
                System.out.println("Invalid Selection, Try Again");
                System.out.println("There are monsters in this room! Which will you fight?");
                for (int i = 0; i < testDungeon.currentRoom.roomMonsters.size(); i++) {
                    System.out.println((i + 1) + ") " + testDungeon.currentRoom.roomMonsters.get(i).name);
                }
                System.out.println("Enter the number of the Monster to fight:");
                monsterSelection = scanner.nextInt();
            }

            Combat.round(winter, testDungeon.currentRoom.roomMonsters.get(monsterSelection - 1));

        }




        if (!winter.isDead) {
            door1.open();
            entranceDoor.exitDungeon();
        }

        /*
        while in current room the menu cli should be up allowing a list of options to be selected
        these options should return values specific to the contents of that room or of the fields within Character
            when a room transition occurs
        exiting the dungeon should be possible, which will bring up a scoring system of some kind
         */



    }
}
