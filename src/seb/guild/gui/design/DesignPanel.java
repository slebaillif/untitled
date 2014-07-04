package seb.guild.gui.design;

import seb.guild.model.dungeon.Coordinates;
import seb.guild.model.dungeon.DungeonArea;
import seb.guild.model.dungeon.DungeonTile;
import seb.guild.model.effects.Effect;
import seb.guild.model.effects.MoveToAnotherMap;
import seb.guild.model.effects.OneTargetTileEffect;
import seb.guild.gui.TileImages;
import seb.guild.model.item.Item;
import seb.guild.model.item.ItemContainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;

import static seb.guild.model.dungeon.DungeonLayout.*;

public class DesignPanel extends JPanel {
    DesignModel model;
    private int visibleHeight = 15;
    private int visibleWidth = 10;
    private Coordinates panelOrigin = new Coordinates(0, 0);
    private int defaultBlockSize = 50;
    private TileImages images = TileImages.getInstance();

    public DesignPanel(DesignModel model) {
        this.model = model;

        visibleHeight = model.getArea().getHeight();
        visibleWidth = model.getArea().getWidth();

        setPreferredSize(new Dimension(visibleWidth * defaultBlockSize, visibleHeight * defaultBlockSize));
        DungeonMouseListener dm = new DungeonMouseListener(model.getArea());
        addMouseListener(dm);
        addMouseMotionListener(dm);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int y = 0; y < visibleHeight; y++) {
            for (int x = 0; x < visibleWidth; x++) {
                if (model.getArea().isInArea(x + panelOrigin.getX(), y + panelOrigin.getY())) {
                    DungeonTile tile = model.getArea().selectTile(x, y, panelOrigin);

                    switch (tile.getTileType()) {
                        case Wall:
                        case Entry:
                        case Empty:
                        case OpenedDoor:
                        case ClosedDoor:
                        default:
                            drawImage(g, x, y, TileImages.getImageFor(tile.getTileType()));
                    }

                    if (tile.getTrigger() != null) {
                        drawImage(g, x, y, images.getCursorRed());
                    }

                    if (model.getArea().selectContentAt(x, y, panelOrigin) != null) {
                        Iterator<Item> i = model.getArea().selectContentAt(x, y, panelOrigin).iterator();
                        while (i.hasNext()) {
                            Item item = i.next();
                            if (item instanceof ItemContainer) {
                                drawImage(g, x, y, images.getChestImg());
                            }
                        }
                    }
                }
            }
        }

    }

    private void drawImage(Graphics g, int x, int y, Image image) {
        g.drawImage(image, x * defaultBlockSize, y * defaultBlockSize, 50, 50, this);
    }

    private class DungeonMouseListener implements MouseListener, MouseMotionListener {
        private final DungeonArea area;

        public DungeonMouseListener(DungeonArea area) {
            this.area = area;
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            int dungeonX = e.getX() / defaultBlockSize + panelOrigin.getX();
            int dungeonY = e.getY() / defaultBlockSize + panelOrigin.getY();

            DungeonTile tile = model.getArea().getTiles()[dungeonX][dungeonY];
            if (!model.getSelectedPattern().equals(Trigger) && !model.getSelectedPattern().equals(Select)) {
                tile.setTileType(model.getSelectedPattern());
            }

            if (model.getSelectedPattern() == Entry) {
                model.getArea().setEntryPoint(new Coordinates(dungeonX, dungeonY));
            }

            if (model.getSelectedPattern().equals(Trigger)) {
                if (model.getTriggerCoordinates() == null) {
                    model.setTriggerCoordinates(new Coordinates(dungeonX, dungeonY));
                } else {
                    DungeonTile triggerTile = area.getTiles()[model.getTriggerCoordinates().getX()][model.getTriggerCoordinates().getY()];
                    Effect effect = model.getTrigger().getEffect();
                    if (effect instanceof OneTargetTileEffect) {
                        // todo check if the target is a door
                        ((OneTargetTileEffect) effect).setTargetTile(new Coordinates(dungeonX, dungeonY));
                    }

                    if (effect instanceof MoveToAnotherMap){
                        JFileChooser openFile = new JFileChooser();
                        int result = openFile.showOpenDialog(null);
                        if (result == JFileChooser.APPROVE_OPTION) {
                            ((MoveToAnotherMap)effect).setTargetMap(openFile.getSelectedFile().getAbsolutePath());
                        }
                    }
                    triggerTile.setTrigger(model.getTrigger());
                    model.setTrigger(null);
                    model.setTriggerCoordinates(null);
                }
            }

            if (model.getSelectedPattern().equals(Select)) {
                model.setSelectedTile(new Coordinates(dungeonX, dungeonY));
                model.fireSelectedTile();
            }
            repaint();
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            int dungeonX = e.getX() / defaultBlockSize + panelOrigin.getX();
            int dungeonY = e.getY() / defaultBlockSize + panelOrigin.getY();
            DungeonTile tile = area.getTiles()[dungeonX][dungeonY];
            if (tile.getTrigger() != null) {

                Effect effect = tile.getTrigger().getEffect();
                Coordinates target = null;
                if (effect instanceof OneTargetTileEffect) {
                    // TODO check visible
                    target = ((OneTargetTileEffect) effect).getTargetTile();
                }
                drawImage(getGraphics(), target.getX(), target.getY(), TileImages.getImageFor(Select));
            }
        }
    }
}

