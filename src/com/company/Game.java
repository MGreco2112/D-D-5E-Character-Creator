package com.company;

import com.company.model.*;
import com.company.services.*;

import java.util.Scanner;

public class Game {
    private final Scanner scanner;

    public Game(Scanner scanner){
        this.scanner = scanner;
    }

    /**
     * Method initializes the game and starts it.
     *
     * TODO add classes Trap, Puzzle, Created Items, Created Monsters, Created Spells
     */
    public void start(){
        PlayerCharacter winter = new PlayerCharacter("Winter", "Bard", 1, 8, 12, 30, 2, 0, true, false, true,
                "Lawful Neutral");

        PlayerCharacterService.addStartingGear(winter);

        System.out.println(winter);




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
        room2.addMonster(MonsterService.get("goblin"));


        testDungeon.addCharacter(winter);

        testDungeon.startDungeon();
    }
}
