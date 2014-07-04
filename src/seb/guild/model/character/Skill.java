package seb.guild.model.character;


import java.util.Map;

public class Skill {
    private String name;
    private Map<Integer, Effect> effectPerSkillLevel;

    public Skill(String name, Map<Integer, Effect> effectPerSkillLevel) {
        this.name = name;
        this.effectPerSkillLevel = effectPerSkillLevel;
    }
}
