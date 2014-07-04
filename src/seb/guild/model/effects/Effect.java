package seb.guild.model.effects;

import seb.guild.model.character.enums.EffectType;
import seb.guild.model.dungeon.DungeonArea;
import seb.guild.gui.DungeonPanel;
import seb.guild.gui.GuildApp;

public interface Effect {
    // add area of effect
    // animation or graphical representation


    public void triggerEffect(DungeonArea area, DungeonPanel panel, GuildApp app);
    public EffectType getEffectType();

}
