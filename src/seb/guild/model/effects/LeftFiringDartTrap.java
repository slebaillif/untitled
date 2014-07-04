package seb.guild.model.effects;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import seb.guild.model.character.enums.EffectType;
import seb.guild.model.dungeon.Coordinates;
import seb.guild.model.dungeon.DungeonArea;
import seb.guild.gui.DungeonPanel;
import seb.guild.gui.GuildApp;
import seb.guild.gui.TrapImages;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "class")
public class LeftFiringDartTrap implements Effect, OneTargetTileEffect {
    EffectType effectType = EffectType.DAMAGE;

    Coordinates firingOrigin;

    public LeftFiringDartTrap() {
    }


    @Override
    public void triggerEffect(DungeonArea area, DungeonPanel panel, GuildApp app) {
        TrapImages images = new TrapImages();
        Coordinates currentDartCoordinates = new Coordinates(firingOrigin.getX(), firingOrigin.getY());
        boolean notEnded = true;

        do {
            currentDartCoordinates.setX(currentDartCoordinates.getX() + 1);
            if (currentDartCoordinates.equals(panel.getPlayerRealCoordinates())) {
                notEnded = false;
                panel.drawImageWithRealCoordinates(currentDartCoordinates, images.getRedBlood());
            } else if (!area.getTiles()[currentDartCoordinates.getX()][currentDartCoordinates.getY()].isWalkable()) {
                notEnded = false;
            } else {
                panel.drawImageWithRealCoordinates(currentDartCoordinates, images.getDartHorizontalFromLeft());
            }
        }
        while (notEnded);
    }

    @Override
    public EffectType getEffectType() {
        return effectType;
    }

    @Override
    public void setTargetTile(Coordinates targetTile) {
        firingOrigin = targetTile;
    }

    @Override
    public Coordinates getTargetTile() {
        return firingOrigin;
    }
}
