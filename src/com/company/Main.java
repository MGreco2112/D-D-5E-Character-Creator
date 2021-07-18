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

        Weapon longsword = new Weapon("Longsword", "Versatile (1d10)", 3, d8);


    }
}
