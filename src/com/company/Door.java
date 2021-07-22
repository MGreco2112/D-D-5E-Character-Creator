package com.company;

// TODO String type (wood, stone, iron, etc), boolean isLocked, Room roomA, Room roomB
public class Door {
    public String type;
    public String locationInRoom;
    public boolean isLocked;
    public boolean isTrapped;
    public Room roomA;
    public Room roomB;

    public Door(String type, String locationInRoom, boolean isLocked, boolean isTrapped, Room roomA, Room roomB) {
        this.type = type;
        this.locationInRoom = locationInRoom;
        this.isLocked = isLocked;
        this.isTrapped = isTrapped;
        this.roomA = roomA;
        this.roomB = roomB;
    }

}
