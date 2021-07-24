package com.company;

import java.util.ArrayList;

public class Dungeon {
    public String name;
    public Room currentRoom;
    public Character activeCharacter;
    public int numberOfRooms;
    ArrayList<Room> rooms = new ArrayList<>();

    public Dungeon(String name, int numberOfRooms) {
        this.name = name;
        this.numberOfRooms = numberOfRooms;
    }

    public void addRoom(Room room) {
        if (rooms.size() < numberOfRooms) {
            room.dungeon = this;
            rooms.add(room);
            System.out.println(room.name + " has been added.");
        }
        else {
            System.out.println(name + " already has " + numberOfRooms + " rooms");
        }
    }

    public void addCharacter(Character character) {
        this.activeCharacter = character;
        System.out.println(character.name + " has been placed at the start of the Dungeon.");
    }

    //TODO get this working with moving rooms
    public void startDungeon() {
        currentRoom = rooms.get(0);
        activeCharacter.currentRoom = currentRoom;
    }

    public void changeCurrentRoom(Room room) {
        currentRoom = room;
        activeCharacter.currentRoom = room;
    }

}
