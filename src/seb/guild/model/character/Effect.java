package seb.guild.model.character;

import seb.guild.model.character.enums.EffectDuration;
import seb.guild.model.character.enums.EffectTrigger;
import seb.guild.model.character.enums.EffectType;

public class Effect {
    private String name;
    private EffectDuration duration;
    private EffectType effectType;
    private EffectTrigger effectTrigger;

    public Effect(String name, EffectDuration duration, EffectType effectType, EffectTrigger effectTrigger) {
        this.name = name;
        this.duration = duration;
        this.effectType = effectType;
        this.effectTrigger = effectTrigger;
    }
}
