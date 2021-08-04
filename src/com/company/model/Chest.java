package com.company.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Chest extends Item{


    Room dungeonRoom;
    int itemsInChest;
    boolean isTrapped;
    List<Item> contents = new ArrayList<>();

    public Chest(String name, int itemsInChest, boolean isTrapped) {
        super(name, "", 0);
        this.itemsInChest = itemsInChest;
        this.isTrapped = isTrapped;
        isWeapon = false;
    }

    public void addItem(Item item) {
        if (contents.size() < itemsInChest) {
            contents.add(item);
        } else {
            System.out.println("Chest is full");
        }
    }

    public void openChest() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number next to the desired item");

        for (int i = 0; i < contents.size(); i++) {
            System.out.println((i + 1) + ") " + contents.get(i).name);
        }

        System.out.println("0) Take All");

        int choice = scanner.nextInt() - 1;

        if (choice != -1) {
            dungeonRoom.dungeon.activeCharacter.addEquipment(contents.get(choice));
            System.out.println(dungeonRoom.dungeon.activeCharacter.name + " takes " + contents.get(choice).name);
            contents.remove(choice);
        } else {
            for (Item item : contents) {
                dungeonRoom.dungeon.activeCharacter.addEquipment(item);
                System.out.println(dungeonRoom.dungeon.activeCharacter.name + " takes " + contents.get(contents.indexOf(item)).name);
            }

            for (int i = 0; i < contents.size(); i++) {
                contents.remove(i);
                i--;
            }
        }
    }

    public String toString() {
        return "Chest";
    }
}
