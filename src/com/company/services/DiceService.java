package com.company.services;

import com.company.model.DiceType;
import com.company.model.Die;
import com.company.model.Gear;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Service to provide us with dice on demand
 * Since phisical dice are only used in DnD to simulate randomness, there isn't a strict need to have a Die object
 * In order to clean up our models, but also allow for a representation of a Die object if needed, I created
 * this class.
 *
 * This way, we can ask for an object, OR we can ask the service to simple roll dice for us.
 * This allowed me to trim Dice as an attribute from other objects, since only the game object should have dice
 * Monsters,Spells, and Players only leverage the behaviors of the dice, and don't need the object
 */
public class DiceService {
    private static final Map<String, Die> diceMap;

    static{
        Map<String, Die> map = new HashMap<>();

        Die dPercent = new Die(100, 100);
        Die d20 = new Die(20, 20);
        Die d12 = new Die(12,12);
        Die d10 = new Die(10,10);
        Die d8 = new Die(8,8);
        Die d6 = new Die(6,6);
        Die d4 = new Die(4,4);
        Die d2 = new Die(2,2);
        Die d1 = new Die(1,1);
        Die d0 = new Die(0, 0);

        map.put("dPercent",dPercent);
        map.put("d20",d20);
        map.put("d12",d12);
        map.put("d10",d10);
        map.put("d8",d8);
        map.put("d6",d6);
        map.put("d4",d4);
        map.put("d2",d2);

        //By using an unmodifiable map, I restrict put/remove calls to the map.
        //If I did not do this, while the diceMap is final, these destructive methods can still be utilized
        diceMap = Collections.unmodifiableMap(map);
    }

    /**
     * Method which will give us a die object if necessary
     * @param key
     * @return
     */
    public static Die get(String key){

        return diceMap.get(key.toUpperCase());
    }

    public static Die get(DiceType diceType){
        return diceMap.get(diceType.name());
    }

    public static int rollType(DiceType diceType){
        return diceMap.get(diceType).roll();
    }

    /**
     * Roll a 20 sided die and return the result
     * @return
     */
    public static int roll_20(){
        return diceMap.get("d20").roll();
    }

    /**
     * Roll a 12 sided die and return the result
     * @return
     */
    public static int roll_12(){
        return diceMap.get("d12").roll();
    }

    /**
     * Roll a 10 sided die and return the result
     * @return
     */
    public static int roll_10(){
        return diceMap.get("d20").roll();
    }

    /**
     * Roll a 8 sided die and return the result
     * @return
     */
    public static int roll_8(){
        return diceMap.get("d20").roll();
    }

    /**
     * Roll a 6 sided die and return the result
     * @return
     */
    public static int roll_6(){
        return diceMap.get("d20").roll();
    }

    /**
     * Roll a 4 sided die and return the result
     * @return
     */
    public static int roll_4(){
        return diceMap.get("d20").roll();
    }

    /**
     * Flip a coin and return the result
     * @return
     */
    public static int roll_2(){
        return diceMap.get("d20").roll();
    }

}
