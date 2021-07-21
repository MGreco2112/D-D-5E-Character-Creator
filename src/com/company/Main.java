package com.company;

public class Main {

    // TODO add classes Dungeon, Room, Created Items, Created Monsters
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
        Spell mage_hand = new Spell("Mage Hand", 0, "Conjuration", 1, 30, "V, S", 60);


        Character winter = new Character("Winter", "Bard", 1, Die.rollStats(), Die.rollStats(), Die.rollStats(),
                Die.rollStats()
        , Die.rollStats(), Die.rollStats(), 8, 12, 30, 2, 0, true, false, true,
                "Lawful Neutral");




        winter.addEquipment(clothes);
        winter.addEquipment(dagger);
        winter.readyWeapon(dagger);
        winter.addProficiency("Dagger");
        winter.addSpell(mage_hand);

        System.out.println(winter);
//
        Monster goblin = new Monster("Goblin", 5, 12, 2, club);


        Combat.round(winter, goblin);



    }
}
