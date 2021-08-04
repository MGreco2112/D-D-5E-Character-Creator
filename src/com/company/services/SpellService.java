package com.company.services;

import com.company.model.*;

import java.util.HashMap;
import java.util.Map;

public class SpellService {
    private Map<String,Spell> spellMap;

    public SpellService(){
        spellMap = initializeSpells();
    }

    //TODO: Pull spells values from API or File, rather than hardcoding
    private Map<String,Spell> initializeSpells(){
        Map<String, Spell> spellMap = new HashMap<>();

        Spell mage_hand = new Spell("Mage Hand", 0, "Conjuration", 1, 30, "V, S", 60, 0);
        Spell magic_missile = new Spell("Magic Missile", 1, "Evocation", 1, 120, "V, S", 0, 4, DiceType.D4, 1);

        spellMap.put(formKey(mage_hand.name),mage_hand);
        spellMap.put(formKey(magic_missile.name),magic_missile);

        return spellMap;
    }

    private String formKey(String name){
        return name.toLowerCase().replace(" ","_");
    }

    public Spell get(String key){
        return spellMap.get(key);
    }
}
