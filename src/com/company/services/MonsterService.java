package com.company.services;

import com.company.model.Die;
import com.company.model.Monster;
import com.company.model.WeaponCollection;

import java.util.HashMap;
import java.util.Map;

/**
 * Class which populates a dictionary of monsters that the application cause use
 *
 * TODO: It is my recommendation that we at some point use maven, and actually use an api to populate DD5E data for us
 */
public class MonsterService {
    private static Map<String, Monster> monsterMap;

    static {
        monsterMap = initializeMonsters();
    }

    //TODO: Pull monster values from API or File, rather than hardcoding
    private static Map<String,Monster> initializeMonsters(){
        Map<String, Monster> monsterMap = new HashMap<>();

        Monster goblin = new Monster("Goblin", "Ugly, Green, Hooked nose, Angry", 5, 12, 2, WeaponCollection.club()
                , 500);
        Monster hobGoblin = new Monster("Hobgoblin", "Ugly, Orange, Scarier than a goblin", 8, 12, 2, WeaponCollection.club()
                , 800);

        monsterMap.put(formKey(goblin.name), goblin);
        monsterMap.put(formKey(hobGoblin.name), hobGoblin);

        return monsterMap;
    }

    private static String formKey(String name){
        return name.toLowerCase().replace(" ","_");
    }

    public static Monster get(String key){
        return monsterMap.get(key);
    }
}
