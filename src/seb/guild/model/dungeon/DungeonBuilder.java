package seb.guild.model.dungeon;

import seb.guild.model.item.ItemBuilder;

import static seb.guild.model.dungeon.DungeonTile.*;

public class DungeonBuilder {
    public DungeonArea getLostTemple(){
        DungeonArea area = new DungeonArea("The lost temple", 20,20);
        area.setSectionLine(3,1, getEmptyTile(), getEmptyTile(), getEmptyTile(), getEmptyTile());
        area.setSectionLine(3,2, getEmptyTile(),getEmptyTile(),getEmptyTile(),getEmptyTile());
        area.setSectionLine(3,3, getEmptyTile(),getEmptyTile(),getEmptyTile(),getEmptyTile(),getEmptyTile(),getEmptyTile(),getEmptyTile(),getEmptyTile(),getEmptyTile(),getEmptyTile(),getEmptyTile(),getEmptyTile(),getEmptyTile(),getEmptyTile(),getEmptyTile(),getEmptyTile());
        area.setSectionLine(3,4, getEmptyTile(),getEmptyTile(),getEmptyTile(),getEmptyTile());
        area.setSectionLine(3,5, getEmptyTile(),getEmptyTile(),getEmptyTile(),getEmptyTile());
        area.setSectionLine(3,6, getWall(),getEmptyTile(),getWall(),getWall());
        area.setSectionLine(3,7, getWall(),getEmptyTile(),getWall(),getWall());
        area.setSectionLine(3,8, getWall(),getEmptyTile(),getWall(),getWall());
        area.setSectionLine(3,9, getWall(),getEmptyTile(),getWall(),getWall());
        area.setEntryPoint(new Coordinates(4,9));

        area.addContent(3,2,new ItemBuilder().getWoodenBox());
        return area;
    }
}
