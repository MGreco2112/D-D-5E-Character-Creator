package com.company;

public class Main {

    // TODO add classes Dungeon, Door, Trap, Puzzle, Created Items, Created Monsters, Created Spells
    public static void main(String[] args) {
        Die dPercent = new Die(100, 100);
        Die d20 = new Die(20, 20);
        Die d12 = new Die(12,12);
        Die d10 = new Die(10,10);
        Die d8 = new Die(8,8);
        Die d6 = new Die(6,6);
        Die d4 = new Die(4,4);
        Die d2 = new Die(2,2);

        Weapon dagger = new Weapon("Dagger", false, "Finesse, light, thrown (20/60)", 1, 1, d4);
        Weapon club = new Weapon("Club", false, "Fight", 2, 1, d4);
        Gear clothes = new Gear("Common Clothes", "Tunic; gray", 3);
        Spell mage_hand = new Spell("Mage Hand", 0, "Conjuration", 1, 30, "V, S", 60, 0);
        Spell magic_missile = new Spell("Magic Missile", 1, "Evocation", 1, 120, "V, S", 0, 4, d4, 1);


        Character winter = new Character("Winter", "Bard", 1, Die.rollStats(), Die.rollStats(), Die.rollStats(),
                Die.rollStats()
        , Die.rollStats(), Die.rollStats(), 8, 12, 30, 2, 0, true, false, true,
                "Lawful Neutral");




        winter.addEquipment(clothes);
        winter.addEquipment(dagger);
        winter.readyWeapon(dagger);
        winter.addProficiency("Dagger");
        winter.addSpell(mage_hand);
        winter.addSpell(magic_missile);

        System.out.println(winter);

        Monster goblin = new Monster("Goblin", "Ugly, Green, Hooked nose, Angry", 5, 12, 2, club);


//        Combat.round(winter, goblin);



        Room room1 = new Room("Entry Way", "A 20' x 20' cave entrance filled with dripping stalactites", 2, false,false,
                false, false);
        Room room2 = new Room("Cave", "A 10' by 50' natural hallway sunken deep into the mountain", 1, true, false,
                false, false);


        Dungeon testDungeon = new Dungeon("Test Dungeon", 2);
        testDungeon.addRoom(room1);
        testDungeon.addRoom(room2);

        Door entranceDoor = new Door("Cave", "Gaping hole in the side of a mountain", 0, false, false, true, room1,
                room1);

        Door door1 = new Door("Wood", "Between the cave mouth and the back passage", 2, false, false, false, room1,
                room2);

        room1.addDoor(entranceDoor);
        room1.addDoor(door1);
        room2.addDoor(door1);
        room2.addMonster(goblin);


        testDungeon.addCharacter(winter);

        testDungeon.startDungeon();

        door1.open();
        Combat.round(winter, winter.currentRoom.roomMonsters.get(0));

        if (!winter.isDead) {
            door1.open();
            entranceDoor.exitDungeon();
        }

        /*
        while in current room the menu cli should be up allowing a list of options to be selected
        these options should return values specific to the contents of that room or of the fields within Character
            when a room transition occurs
        exiting the dungeon should be possible, which will bring up a scoring system of some kind
         */



    }
}
