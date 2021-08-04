package com.company.services;

import com.company.model.Gear;
import com.company.model.Monster;
import com.company.model.WeaponCollection;

import java.util.HashMap;
import java.util.Map;

public class GearService {
    private static final Map<String, Gear> gearMap;

    static {
        gearMap = initializeGear();
    }

    //TODO: Pull gear values from API or File, rather than hardcoding
    private static Map<String,Gear> initializeGear(){
        Map<String, Gear> gearMap = new HashMap<>();

        Gear clothes = new Gear("Common Clothes", "Tunic; gray", 3);

        gearMap.put(formKey(clothes.name),clothes);

        return gearMap;
    }

    private static String formKey(String name){
        return name.toLowerCase().replace(" ","_");
    }

    public static Gear get(String key){
        return gearMap.get(key);
    }
}
