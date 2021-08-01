package com.company;

public class WeaponCollection {
    public static Die dPercent = new Die(100, 100);
    public static Die d20 = new Die(20, 20);
    public static Die d12 = new Die(12,12);
    public static Die d10 = new Die(10,10);
    public static Die d8 = new Die(8,8);
    public static Die d6 = new Die(6,6);
    public static Die d4 = new Die(4,4);
    public static Die d2 = new Die(2,2);
    public static Die d1 = new Die(1,1);
    public static Die d0 = new Die(0, 0);

    //Simple Melee Weapons
    public static Weapon club() {
        return new Weapon("Club", false, "Light", 2, 1, d4);
    }

    public static Weapon dagger() {
        return new Weapon("Dagger", false, "Finesse, Light, Thrown (20/60)", 1, 1, d4);
    }

    public static Weapon greatclub() {
        return new Weapon("Greatclub", false, "Two-Handed", 10, 1, d8);
    }

    public static Weapon handaxe() {
        return new Weapon("Handaxe", false, "Light, Thrown (20/60)", 2, 1, d6);
    }

    public static Weapon javelin() {
        return new Weapon("Javelin", false, "Thrown (30/120)", 2, 1, d6);
    }

    public static Weapon lightHammer() {
        return new Weapon("Light Hammer", false, "Light, Thrown (20/60)", 2, 1, d4);
    }

    public static Weapon mace() {
        return new Weapon("Mace", false, "-", 4, 1, d6);
    }

    public static Weapon quarterstaff() {
        return new Weapon("Quarterstaff", false, "Versatile (1d8)", 4,1, d6);
    }

    public static Weapon sickle() {
        return new Weapon("Sickle", false, "Light", 2, 1, d4);
    }

    public static Weapon spear() {
        return new Weapon("Spear", false, "Thrown (20/60), Versatile (1d8)", 3, 1, d6);
    }

    //Simple Ranged Weapons

    public static Weapon lightCrossbow() {
        return new Weapon("Light Crossbow", true, "Range (80/320), Loading, Two-Handed", 5, 1, d8);
    }

    public static Weapon dart() {
        return new Weapon("Dart", false, "Finesse, Thrown (20,60)", 0, 1, d4);
    }

    public static Weapon shortbow() {
        return new Weapon("Shortbow", true, "Range (80/320), Two-Handed", 2, 1, d6);
    }

    public static Weapon sling() {
        return new Weapon("Sling", true, "Range (30/120)", 0, 1, d4);
    }

    //Martial Melee Weapons

    public static Weapon battleaxe() {
        return new Weapon("Battleaxe", false, "Versatile (1d10)", 4, 1, d8);
    }

    public static Weapon flail() {
        return new Weapon("Flail", false, "-", 2, 1, d8);
    }

    public static Weapon glaive() {
        return new Weapon("Glaive", false, "Heavy, Reach, Two-Handed", 6, 1, d10);
    }

    public static Weapon greataxe() {
        return new Weapon("Greataxe", false, "Heavy, Two-Handed", 7, 1, d12);
    }

    public static Weapon greatsword() {
        return new Weapon("Greatsword", false, "Heavy, Two-Handed", 7, 1, d12);
    }

    public static Weapon halberd() {
        return new Weapon("Halberd",false, "Heavy, Reach, Two-Handed", 6, 1, d10);
    }

    public static Weapon lance() {
        return new Weapon("Lance", false, "Reach, Special", 6, 1, d12);
    }

    public static Weapon longsword() {
        return new Weapon("Longsword", false, "Versatile (1d10)", 3, 1, d8);
    }

    public static Weapon maul() {
        return new Weapon("Maul", false, "Heavy, Two-Handed", 10, 2, d6);
    }

    public static Weapon morningstar() {
        return new Weapon("Morningstar", false, "-", 4, 1, d8);
    }

    public static Weapon pike() {
        return new Weapon("Pike", false, "Heavy, Reach, Two-Handed", 18, 1, d10);
    }

    public static Weapon rapier() {
        return new Weapon("Rapier", false, "Finesse", 2, 1, d8);
    }

    public static Weapon scimitar() {
        return new Weapon("Scimitar", false, "Finesse, Light", 3, 1, d6);
    }

    public static Weapon shortsword() {
        return new Weapon("Shortsword", false, "Finesse, Light", 3, 1, d6);
    }

    public static Weapon trident() {
        return new Weapon("Trident", false, "Thrown (20/60), Versatile (1d8)", 4, 1, d6);
    }

    public static Weapon warPick() {
        return new Weapon("War Pick", false, "-", 2, 1, d8);
    }

    public static Weapon warhammer() {
        return new Weapon("Warhammer", false, "Versatile (1d10)", 2, 1, d8);
    }

    public static Weapon whip() {
        return new Weapon("Whip", false, "Finesse, Reach", 3, 1, d4);
    }

    //Ranged Martial Weapons

    public static Weapon blowgun() {
        return new Weapon("Blowgun", true, "Range (25/100), Loading", 1, 1, d1);
    }

    public static Weapon handCrossbow() {
        return new Weapon("Hand Crossbow", true, "Range (30/120), Light, Loading", 3, 1, d6);
    }

    public static Weapon heavyCrossbow() {
        return new Weapon("Heavy Crossbow", true, "Range (100/400), Heavy, Loading, Two-Handed", 18, 1, d10);
    }

    public static Weapon longbow() {
        return new Weapon("Longbow", true, "Range (150/600), Heavy, Two-Handed", 2, 1, d8);
    }

    public static Weapon net() {
        return new Weapon("Net", false, "Special, Thrown (5/15)", 3, 0, d0);
    }


}
