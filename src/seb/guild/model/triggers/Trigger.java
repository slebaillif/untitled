package seb.guild.model.triggers;

import seb.guild.model.effects.Effect;

public class Trigger {
    TriggerTiming when;
    Effect effect;

    public Trigger() {
    }

    public Trigger(TriggerTiming when, Effect effect) {
        this.when = when;
        this.effect = effect;
    }

    public TriggerTiming getWhen() {
        return when;
    }

    public void setWhen(TriggerTiming when) {
        this.when = when;
    }

    public Effect getEffect() {
        return effect;
    }

    public void setEffect(Effect effect) {
        this.effect = effect;
    }
}
