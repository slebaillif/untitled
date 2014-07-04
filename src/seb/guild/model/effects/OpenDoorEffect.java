package seb.guild.model.effects;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import seb.guild.model.character.enums.EffectType;
import seb.guild.model.dungeon.Coordinates;
import seb.guild.model.dungeon.DungeonArea;
import seb.guild.model.dungeon.DungeonTile;
import seb.guild.gui.DungeonPanel;
import seb.guild.gui.GuildApp;

import static seb.guild.model.dungeon.DungeonLayout.OpenedDoor;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "class")
public class OpenDoorEffect implements Effect, OneTargetTileEffect {
    private Coordinates doorCoordinates;
    EffectType effectType;

    public OpenDoorEffect() {
    }

    public OpenDoorEffect(Coordinates doorCoordinates) {
        this.doorCoordinates = doorCoordinates;
    }

    @Override
    public void triggerEffect(DungeonArea area, DungeonPanel panel, GuildApp app) {
        DungeonTile tile = area.getTiles()[doorCoordinates.getX()][doorCoordinates.getY()];
        tile.setTileType(OpenedDoor);
        panel.repaint();
    }

    public EffectType getEffectType() {
        return effectType;
    }

    public void setEffectType(EffectType effectType) {
        this.effectType = effectType;
    }

    @Override
    public void setTargetTile(Coordinates targetTile) {
        doorCoordinates = targetTile;
    }

    @Override
    public Coordinates getTargetTile() {
        return doorCoordinates;
    }
}
