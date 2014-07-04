package seb.guild.gui.design;

import seb.guild.model.dungeon.Coordinates;
import seb.guild.model.dungeon.DungeonTile;
import seb.guild.model.effects.OneTargetTileEffect;

import javax.swing.*;
import java.awt.*;

public class DetailsPanel extends JPanel implements SelectedTileListener {
    DesignModel model;

    JLabel coord;
    JLabel tileType;
    JLabel targetCoord;

    public DetailsPanel(DesignModel model) {
        this.model = model;
        setPreferredSize(new Dimension(5 * 50, 5 * 50));
        model.registerListenerSelectedTile(this);

        coord = new JLabel("Coordinates");
        tileType = new JLabel("Tile type");
        targetCoord = new JLabel("");
        add(coord, BorderLayout.CENTER);
        add(tileType, BorderLayout.CENTER);
        add(targetCoord, BorderLayout.CENTER);
        coord.setVisible(true);
        tileType.setVisible(true);
        targetCoord.setVisible(false);
    }

    @Override
    public void paintComponent(Graphics g) {
        if (model.getSelectedTile() != null) {
            DungeonTile tile = model.getArea().selectTile(model.getSelectedTile());
            coord.setText("Coordinates X = " + model.getSelectedTile().getX() + " Y = " + model.getSelectedTile().getY());
            tileType.setText("Tile type = " + tile.getTileType());
            if (tile.getTrigger() != null) {
                if (tile.getTrigger().getEffect() instanceof OneTargetTileEffect) {
                    OneTargetTileEffect tileEffect = (OneTargetTileEffect) tile.getTrigger().getEffect();
                    targetCoord.setText("Target X = " + tileEffect.getTargetTile().getX() + " Y = " + tileEffect.getTargetTile().getY());
                    targetCoord.setVisible(true);
                }
            } else {
                targetCoord.setVisible(false);
            }

        }

    }

    @Override
    public void tileSelected(Coordinates selectedTile) {
        repaint();
    }
}
