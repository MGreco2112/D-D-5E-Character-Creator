package com.company;

// TODO String type (wood, stone, iron, etc), boolean isLocked, Room roomA, Room roomB, add entrance type that will
//  exit dungeon when passed through
public class Door {
    public String type;
    public String locationInRoom;
    public boolean isLocked;
    public boolean isTrapped;
    public int hitPoints;
    public Room roomA;
    public Room roomB;
    public Dungeon dungeon;


    public Door(String type, String locationInRoom, int hitPoints, boolean isLocked, boolean isTrapped, Room roomA,
                Room roomB) {
        this.type = type;
        this.locationInRoom = locationInRoom;
        this.hitPoints = hitPoints;
        this.isLocked = isLocked;
        this.isTrapped = isTrapped;
        this.roomA = roomA;
        this.roomB = roomB;
        this.dungeon = roomA.dungeon;
    }

    public void open() {

        if (!isLocked && !isTrapped) {
            System.out.println("The door is unlocked!");
            if (dungeon.currentRoom == roomA) {
                dungeon.changeCurrentRoom(roomB);
            } else {
                dungeon.changeCurrentRoom(roomB);
            }
        } else if (!isTrapped) {
            System.out.println("The door is Locked!");
        } else if (!isLocked) {
            System.out.println("A Trap has been set off!");
        }

    }
}
