package com.company;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

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

    public void endGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(activeCharacter.name + ", Score: score system to be implemented in a later build.");
        System.out.println("Do you want to play again?\n(y)es or (n)o");
        String choice = scanner.nextLine();
        switch (choice.toLowerCase(Locale.ROOT)) {
            case "y" :
                startDungeon();
                break;
            case "n" :
                System.out.println("Thank you for playing!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid selection");
                endGame();
        }
    }

}
