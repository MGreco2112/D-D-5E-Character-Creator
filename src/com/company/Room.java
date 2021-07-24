package com.company;

import java.util.ArrayList;
import java.util.Scanner;

// TODO Create a dungeon Room that can possibly contain a Monster, Item(s), Puzzle(s), *Trap(s)
public class Room {
    private Scanner scanner = new Scanner(System.in);
    public String name;
    public String description;
    public Dungeon dungeon;
    public int numberOfDoors;
    public boolean activeMonster;
    public boolean activeItem;
    public boolean activeTrap;
    public boolean activePuzzle;
    public ArrayList<Monster> roomMonsters = new ArrayList<>();
    public ArrayList<Item> roomItems = new ArrayList<>();
    public ArrayList<Door> doors = new ArrayList<>();

    public Room(String name, String description, int numberOfDoors, boolean activeMonster, boolean activeItem,
                boolean activeTrap,
                boolean activePuzzle) {
        this.name = name;
        this.description = description;

        if (numberOfDoors <= 0) {
            this.numberOfDoors = 1;
        } else {
            this.numberOfDoors = numberOfDoors;
        }

        this.activeMonster = activeMonster;
        this.activeItem = activeItem;
        this.activeTrap = activeTrap;
        this.activePuzzle = activePuzzle;
    }

    public String addMonster(Monster monster) {
        roomMonsters.add(monster);

        return monster.name + " has been added.";
    }

    public String addItem(Item item) {
        roomItems.add(item);

        return item.name + " has been added.";
    }

    //TODO Use Scanner to build a new instance of Door and add it to the doors ArrayList
    public String addDoor(Door door) {
        doors.add(door);

        return "Door to " + door.roomB + " has been added";
    }

    public void combatCheck() {
        if (activeMonster) {
            int counter = 1;
            System.out.println("Which monster will you engage with?");
            for (Monster monster : roomMonsters) {
                System.out.println(counter + ") " + monster.name);
                counter++;
            }
            int choice = scanner.nextInt() - 1;

            Combat.round(dungeon.activeCharacter, roomMonsters.get(choice));

        }
    }


    public String toString() {
        return name + ", Number of Doors: " + numberOfDoors + ", Monsters: " + roomMonsters + ", Items: " + roomItems;
    }
}
