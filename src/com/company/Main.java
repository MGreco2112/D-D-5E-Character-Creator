package com.company;

public class Main {

    public static void main(String[] args) {
        Die d20 = new Die(20, 20);
        Die d12 = new Die(12,12);
        Die d10 = new Die(10,10);
        Die d8 = new Die(8,8);
        Die d6 = new Die(6,6);
        Die d4 = new Die(4,4);
        Die d2 = new Die(2,2);

        Weapon longsword = new Weapon("Longsword", "Versatile (1d10)", 3, 1, d8);

//        System.out.println("Strength: " + Die.rollStats());
//        System.out.println("Dexterity: " + Die.rollStats());
//        System.out.println("Constitution: " + Die.rollStats());
//        System.out.println("Intelligence: " + Die.rollStats());
//        System.out.println("Wisdom: " + Die.rollStats());
//        System.out.println("Charisma: " + Die.rollStats());

        Character winter = new Character("Winter", "Bard", 1, Die.rollStats(), Die.rollStats(), Die.rollStats(), Die.rollStats()
        , Die.rollStats(), Die.rollStats(), 8, 12, 30, 2, 0, true, false, true,
                "Chaotic Neutral");

        System.out.println(winter);


    }
}
