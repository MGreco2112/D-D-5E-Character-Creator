package com.company;

import java.util.Locale;
import java.util.Scanner;

public class Combat {
    /* Currently does not break in the right location. Goes to flee after combatant falls
    */



    public static void round(Character player ,Monster encounter) {
        Die d20 = new Die(20,20);
        Scanner scanner = new Scanner(System.in);

        System.out.println(encounter.name + " stands in your path! What do you want to do?\n(l)ook\n(a)ttack\n(r)un");

        String choice = scanner.nextLine();

        switch (choice.toLowerCase(Locale.ROOT)) {
            case "l" :
                System.out.println(encounter.name + " stands before you. A description will accompany the Monster " +
                        "class in a future build.");
                round(player,encounter);
                break;

            case "a" :
                int playerInitiative = d20.roll();
                int encounterInitiative = d20.roll();

                if (playerInitiative >= encounterInitiative) {
                    player.attack(player.readiedWeapon, encounter);
                    if (!encounter.isDead) {
                        encounter.attack(encounter.weapon, player);
                    } else {
                        encounter.checkStatus();
                        break;
                    }
                } else if (playerInitiative < encounterInitiative) {
                    encounter.attack(encounter.weapon, player);
                    if (!player.isDead) {
                        player.attack(player.readiedWeapon, encounter);
                    } else {
                        player.checkStatus();
                        break;
                    }
                }

                if (!player.isDead && !encounter.isDead) {
                    round(player, encounter);
                }
                break;

            case "r" :
                System.out.println(player.name + " attempts to flee!");

                int attackOfOpportunity = d20.roll();

                if (attackOfOpportunity >= player.armorClass) {
                    System.out.println("The attempt failed!");
                    int damageRoll = (encounter.weapon.damageCode.roll() * encounter.weapon.numberOfDice);
                    player.hitPoints -= damageRoll;
                    System.out.println(player.name + " takes " + damageRoll + " points of damage");
                    player.checkStatus();
                    if (player.isDead) {
                        break;
                    } else {
                        System.out.println(player.name + " has " + player.hitPoints + " hit points remaining.");
                    }
                    round(player,encounter);
                    break;
                } else {
                    System.out.println(player.name + " has escaped the encounter!");
                    break;
                }
            default :
                System.out.println("Invalid entry");
                round(player,encounter);
        }
    }
}
