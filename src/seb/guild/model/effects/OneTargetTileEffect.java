package seb.guild.model.effects;

import seb.guild.model.dungeon.Coordinates;

public interface OneTargetTileEffect {
    void setTargetTile(Coordinates targetTile);
    Coordinates getTargetTile();
}
