package seb.guild.model.character.builder;

import seb.guild.model.character.Vocation;
import seb.guild.model.character.enums.Skills;
import seb.guild.model.character.enums.Vocations;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static seb.guild.model.character.enums.Vocations.*;

public class VocationBuilder {
    private Map<Vocations, Vocation> vocationMap = new HashMap<>();

     public VocationBuilder(){
         vocationMap.put(Warrior, createWarriorVocation());
         vocationMap.put(Priest, createPriestVocation());
         vocationMap.put(Rogue, createRogueVocation());
         vocationMap.put(Mage, createMageVocation());
     }

    private Vocation createMageVocation() {
        Set<Skills> skills = new HashSet<>(3);
        skills.add(Skills.FIREMAGIC);
        Vocation w = new Vocation(Mage, skills);
        return w;
    }

    private Vocation createRogueVocation() {
        Set<Skills> skills = new HashSet<>(3);
        skills.add(Skills.OPENLOCKS);
        Vocation w = new Vocation(Rogue, skills);
        return w;
    }

    private Vocation createPriestVocation() {
        Set<Skills> skills = new HashSet<>(3);
        skills.add(Skills.HEAL);
        Vocation w = new Vocation(Priest, skills);
        return w;
    }

    private Vocation createWarriorVocation() {
        Set<Skills> skills = new HashSet<>(3);
        skills.add(Skills.SWORD);
        Vocation w = new Vocation(Warrior, skills);
        return w;
    }


    public Vocation getVocation(Vocations vocation) {
        return vocationMap.get(vocation);
    }
}
