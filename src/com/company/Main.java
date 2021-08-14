package com.company;


import com.company.model.*;


import java.util.*;

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

        PlayerCharacter currentPlayer = PlayerCharacter.createPlayerCharacter();




        currentPlayer.addEquipment(WeaponCollection.dagger());
        currentPlayer.addEquipment(clothes);
        currentPlayer.readyWeapon((Weapon) currentPlayer.gear.get(0));
        currentPlayer.addProficiency("Dagger");
        currentPlayer.addProficiency("Mace");
        currentPlayer.addSpell(mage_hand);
        currentPlayer.addSpell(magic_missile);

        System.out.println(currentPlayer);

        Monster goblin = new Monster("Goblin", "Ugly, Green, Hooked nose, Angry", 5, 12, 2, WeaponCollection.club()
        , 500);






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
        room2.addMonster(goblin);


        testDungeon.addCharacter(currentPlayer);

        testDungeon.startDungeon();


    }
}
