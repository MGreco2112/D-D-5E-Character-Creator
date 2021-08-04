package com.company.services;

import com.company.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Service that can be called to build a player Character
 *
 * This is service is meant to offload the burden of initializing a character
 *
 * FOR EXISTING CHARACTERS:
 * - All you need to do is pass in the Character, pre-determined gear will be added
 *
 */
public class PlayerCharacterService {
    private static final DiceService diceService;
    private static final GearService gearService;
    private static final SpellService spellService;

    static {
        diceService = new DiceService();
        gearService = new GearService();
        spellService = new SpellService();
    }

    //Using code that existed in orginal game loop. We should change later
    public static void addStartingGear(PlayerCharacter player){
        player.addEquipment(WeaponCollection.dagger());
        player.addEquipment(gearService.get("common_clothes"));
        player.readyWeapon((Weapon) player.gear.get(0));
        player.addProficiency("Dagger");
        player.addProficiency("Mace");
        player.addSpell(spellService.get("mage_hand"));
        player.addSpell(spellService.get("magic_missile"));
    }
}
