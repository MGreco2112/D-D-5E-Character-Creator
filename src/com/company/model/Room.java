package com.company.model;

import java.util.ArrayList;

// TODO Create a dungeon Room that can possibly contain a Monster, Item(s), Puzzle(s), *Trap(s)
public class Room {
    //Removed scanner as scanner wasn't used. HOWEVER. In the future we should pass the scanner into the constructor
    public String name;
    public String description;
    public Dungeon dungeon;
    public int searchTargetNumber;
    public int numberOfDoors;
    public boolean activeMonster;
    public boolean activeItem;
    public boolean activeTrap;
    public boolean activePuzzle;
    public boolean hasBeenSearched = false;
    public ArrayList<Monster> roomMonsters = new ArrayList<>();
    public ArrayList<Item> roomItems = new ArrayList<>();
    public ArrayList<Door> doors = new ArrayList<>();

    public Room(String name, String description, int numberOfDoors, boolean activeMonster, boolean activeItem,
                boolean activeTrap,
                boolean activePuzzle, int searchTargetNumber) {
        this.name = name;
        this.description = description;

        this.searchTargetNumber = searchTargetNumber;

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

    /**
     * Allows you to add multiples of the same monster to a room
     * @param monster the monster
     * @param count how many to add
     * @return
     */
    public String addMonster(Monster monster, int count){
        for(int i = 0; i<count; i++){
            roomMonsters.add(monster);
        }

        return count+" number of "+monster.name + " has been added.";
    }

    public String addItem(Item item) {
        roomItems.add(item);

        if (item instanceof Chest) {
            ((Chest) item).dungeonRoom = this;
        }

        if (roomItems.size() != 0) {
            activeItem = true;
        }

        return item.name + " has been added.";
    }

    //TODO Use Scanner to build a new instance of Door and add it to the doors ArrayList
    public String addDoor(Door door) {
        doors.add(door);

        return "Door to " + door.roomB + " has been added";
    }



    public String toString() {
        return name + ", Number of Doors: " + numberOfDoors + ", Monsters: " + roomMonsters + ", Items: " + roomItems;
    }
}
