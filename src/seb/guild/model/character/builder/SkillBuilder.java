package seb.guild.model.character.builder;

import seb.guild.model.character.*;
import seb.guild.model.character.enums.EffectDuration;
import seb.guild.model.character.enums.EffectTrigger;
import seb.guild.model.character.enums.EffectType;
import seb.guild.model.character.enums.Skills;

import java.util.HashMap;
import java.util.Map;

public class SkillBuilder {
    Map<Skills, Map<Integer, Effect>> skills;

    public SkillBuilder(){
        skills.put(Skills.SWORD, createHealSkill());
        skills.put(Skills.HEAL, createHealSkill());
        skills.put(Skills.OPENLOCKS, createOpenLocksSkill());
        skills.put(Skills.FIREMAGIC, createFireMagicSkill());
    }

    private Map<Integer, Effect> createFireMagicSkill() {
        Map<Integer, Effect> effects = new HashMap<Integer, Effect>();
        effects.put(0, new Effect("flame", EffectDuration.INSTANT, EffectType.DAMAGE, EffectTrigger.SKILL_USE));
        return effects;
    }

    public Map<Integer, Effect> getSkillEffects(Skills skill){
        return skills.get(skill);
    }

    private Map<Integer, Effect> createOpenLocksSkill() {
        Map<Integer, Effect> effects = new HashMap<Integer, Effect>();
        effects.put(0, new Effect("open lock", EffectDuration.INSTANT, EffectType.OPEN, EffectTrigger.SKILL_USE));
        return effects;
    }

    private Map<Integer, Effect> createSwordSkill(){
        Map<Integer, Effect> effects = new HashMap<Integer, Effect>();
        effects.put(0, new Effect("sword attack", EffectDuration.INSTANT, EffectType.DAMAGE, EffectTrigger.ATTACK));
        return effects;
    }

    private Map<Integer, Effect> createHealSkill(){
        Map<Integer, Effect> effects = new HashMap<Integer, Effect>();
        effects.put(0, new Effect("light heal", EffectDuration.INSTANT, EffectType.HEAL, EffectTrigger.SKILL_USE));
        return  effects;
    }
}
