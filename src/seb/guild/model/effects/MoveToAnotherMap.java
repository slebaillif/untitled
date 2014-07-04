package seb.guild.model.effects;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import seb.guild.model.character.enums.EffectType;
import seb.guild.model.dungeon.DungeonArea;
import seb.guild.gui.DungeonPanel;
import seb.guild.gui.GuildApp;

import java.awt.*;
import java.io.File;
import java.io.IOException;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "class")
public class MoveToAnotherMap implements Effect {
    String targetMap;


    @Override
    public void triggerEffect(DungeonArea area, DungeonPanel panel, GuildApp app) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enableDefaultTyping();
        try {
            DungeonArea a = mapper.readValue(new File(targetMap), DungeonArea.class);
            app.setArea(a);
            app.setVisible(false);
            app.remove(panel);
            app.getContentPane().add(new DungeonPanel(a, app), BorderLayout.CENTER);
            app.setVisible(true);
            app.getRootPane().paintComponents(app.getGraphics());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public EffectType getEffectType() {
        return EffectType.MOVE;
    }

    public void setEffectType(String type) {
    }

    public String getTargetMap() {
        return targetMap;
    }

    public void setTargetMap(String targetMap) {
        this.targetMap = targetMap;
    }
}
