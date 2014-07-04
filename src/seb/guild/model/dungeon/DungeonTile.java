package seb.guild.model.dungeon;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import seb.guild.model.item.Item;
import seb.guild.model.triggers.Trigger;

import java.util.LinkedList;

import static seb.guild.model.dungeon.DungeonLayout.*;

@JsonIgnoreProperties({"walkable"})
public class DungeonTile {

    private boolean wasSeenByPlayer = false;
    private DungeonLayout tileType;
    private LinkedList<Item> tileContent;
    private Trigger trigger = null;
    // state of doors/lockable doors/sercret doors ? as content ?


    public DungeonTile() {
    }

    public DungeonTile(DungeonLayout tileType) {
        this.tileType = tileType;
        this.tileContent = new LinkedList<>();
        this.wasSeenByPlayer = false;
    }

    public DungeonTile(DungeonLayout tileType, LinkedList<Item> tileContent) {
        this.tileType = tileType;
        this.tileContent = tileContent;
    }

    public boolean isWalkable() {
        return (tileType.equals(Empty) || tileType.equals(OpenedDoor) ||tileType.equals(StairsUp) ||tileType.equals(StairsDown))
                &&
                tileContent.stream().filter(p -> p.isOccupiesWholeTile()).count() <= 0;
    }

    public LinkedList<Item> getTileContent() {
        return tileContent;
    }

    public DungeonLayout getTileType() {
        return tileType;
    }

    public static DungeonTile getWall() {
        return new DungeonTile(DungeonLayout.Wall);
    }

    public static DungeonTile getEmptyTile() {
        return new DungeonTile(Empty);
    }

    public boolean isWasSeenByPlayer() {
        return wasSeenByPlayer;
    }

    public void setWasSeenByPlayer(boolean wasSeenByPlayer) {
        this.wasSeenByPlayer = wasSeenByPlayer;
    }

    public static DungeonTile get(DungeonLayout filler) {
        return new DungeonTile(filler);
    }

    public void setTileType(DungeonLayout tileType) {
        this.tileType = tileType;
    }

    public void setTileContent(LinkedList<Item> tileContent) {
        this.tileContent = tileContent;
    }

    public Trigger getTrigger() {
        return trigger;
    }

    public void setTrigger(Trigger trigger) {
        this.trigger = trigger;
    }

}
