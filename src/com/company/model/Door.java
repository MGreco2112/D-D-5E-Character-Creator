package com.company.model;

import java.util.Locale;
import java.util.Scanner;

// TODO String type (wood, stone, iron, etc), boolean isLocked, Room roomA, Room roomB, add entrance type that will
//  exit dungeon when passed through

//TODO REMEMBER!!! Doors can only work if they are added to the room after the room has been added to the dungeon


public class Door {
    public String type;
    public String locationInRoom;
    public boolean isEntrance;
    public boolean isLocked;
    public boolean isTrapped;
    public int hitPoints;
    public Room roomA;
    public Room roomB;
    public Dungeon dungeon;


    public Door(String type, String locationInRoom, int hitPoints, boolean isLocked, boolean isTrapped, boolean isEntrance,
                Room roomA,
                Room roomB) {
        this.type = type;
        this.locationInRoom = locationInRoom;
        this.hitPoints = hitPoints;
        this.isLocked = isLocked;
        this.isTrapped = isTrapped;
        this.isEntrance = isEntrance;
        this.roomA = roomA;
        this.roomB = roomB;
        this.dungeon = roomA.dungeon;
    }

    public void open() {
        System.out.println(dungeon.activeCharacter.name + " tries the door...");
        if (!isEntrance) {
            if (!isLocked && !isTrapped) {
                System.out.println("The door is unlocked!");
                if (dungeon.currentRoom == roomA) {
                    dungeon.changeCurrentRoom(roomB);
                } else {
                    dungeon.changeCurrentRoom(roomA);
                }
                System.out.println(dungeon.activeCharacter.name + " enters the next Room");
                System.out.println(dungeon.currentRoom.description);
                if (dungeon.currentRoom.activeMonster) {
                    System.out.println("There are Monsters nearby");
                }
            } else if (!isTrapped) {
                System.out.println("The door is Locked!");
            } else if (!isLocked) {
                System.out.println("A Trap has been set off!");
            }
        }

    }

    public void exitDungeon() {
        if (isEntrance) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Are you sure you want to end the game?");
            String input = scanner.nextLine();
            switch (input.toLowerCase(Locale.ROOT)) {
                case "y":
                    dungeon.endGame();
                    break;
                case "n":
                    System.out.println(dungeon.activeCharacter.name + " continues onward!");
                    break;
                default:
                    System.out.println("Invalid selection");
                    exitDungeon();
            }
        }
    }
}
