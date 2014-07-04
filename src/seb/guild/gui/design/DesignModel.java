package seb.guild.gui.design;

import seb.guild.model.dungeon.Coordinates;
import seb.guild.model.dungeon.DungeonArea;
import seb.guild.model.dungeon.DungeonLayout;
import seb.guild.model.triggers.Trigger;

import java.util.ArrayList;
import java.util.List;

public class DesignModel {
    Coordinates selectedTile = null;
    DungeonLayout selectedPattern = DungeonLayout.Empty;
    DungeonArea area;

    Trigger trigger = null;
    Coordinates triggerCoordinates = null;
    Coordinates triggerTarget = null;

    List<SelectedTileListener> listeners = new ArrayList<>();

    public DungeonArea getArea() {
        return area;
    }

    public void setArea(DungeonArea area) {
        this.area = area;
    }

    public DungeonLayout getSelectedPattern() {
        return selectedPattern;
    }

    public void setSelectedPattern(DungeonLayout selectedPattern) {
        this.selectedPattern = selectedPattern;
    }

    public Trigger getTrigger() {
        return trigger;
    }

    public void setTrigger(Trigger trigger) {
        this.trigger = trigger;
    }

    public void setTriggerCoordinates(Coordinates triggerCoordinates) {
        this.triggerCoordinates = triggerCoordinates;
    }

    public void setTriggerTarget(Coordinates triggerTarget) {
        this.triggerTarget = triggerTarget;
    }

    public Coordinates getTriggerCoordinates() {
        return triggerCoordinates;
    }

    public Coordinates getTriggerTarget() {
        return triggerTarget;
    }

    public Coordinates getSelectedTile() {
        return selectedTile;
    }

    public void setSelectedTile(Coordinates selectedTile) {
        this.selectedTile = selectedTile;
    }

    public void fireSelectedTile() {
        for(SelectedTileListener listener:listeners){
            listener.tileSelected(getSelectedTile());
        }
    }

    public void registerListenerSelectedTile(SelectedTileListener listener) {
        listeners.add(listener);
    }
}
