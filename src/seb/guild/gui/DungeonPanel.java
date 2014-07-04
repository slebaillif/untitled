package seb.guild.gui;

import seb.guild.model.dungeon.Coordinates;
import seb.guild.model.dungeon.DungeonArea;
import seb.guild.model.dungeon.DungeonTile;
import seb.guild.model.item.Item;
import seb.guild.model.item.ItemContainer;
import seb.guild.model.triggers.Trigger;
import seb.guild.model.triggers.TriggerTiming;
import seb.guild.view.item.ItemContainerView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Optional;

import static seb.guild.model.dungeon.DungeonLayout.Empty;
import static seb.guild.model.dungeon.DungeonLayout.Wall;

public class DungeonPanel extends JPanel {
    private final MouseListener mouseListener;
    private final TileImages images;
    DungeonArea area;
    Coordinates playerScreenCoord;

    private GuildApp app;
    Coordinates panelOrigin = new Coordinates(0, 0);
    int visibleWidth = 15;
    int visibleHeight = 15;

    int defaultBlockSize = 50;


    public DungeonPanel(DungeonArea area, GuildApp app) {
        this.app = app;
        images = TileImages.getInstance();

        setPreferredSize(new Dimension(visibleWidth * defaultBlockSize, visibleHeight * defaultBlockSize));
        this.area = area;
        this.playerScreenCoord = new Coordinates(area.getEntryPoint().getX(), area.getEntryPoint().getY());
        this.mouseListener = new DungeonMouseListener(area);
        this.setFocusable(true);
        this.setEnabled(true);

        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "up");
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "down");
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "left");
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "right");
        Action up = new UpActionController(this);
        Action down = new DownActionController(this);
        Action right = new RightActionController(this);
        Action left = new LeftActionController(this);
        this.getActionMap().put("up", up);
        this.getActionMap().put("down", down);
        this.getActionMap().put("left", left);
        this.getActionMap().put("right", right);

        this.addMouseListener(mouseListener);
    }

    public Coordinates getPlayerRealCoordinates() {
        return new Coordinates(playerScreenCoord.getX() + panelOrigin.getX(), playerScreenCoord.getY() + panelOrigin.getX());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        updateFogOfWar(2);
        for (int y = 0; y < visibleHeight; y++) {
            for (int x = 0; x < visibleWidth; x++) {
                if (area.isInArea(x + panelOrigin.getX(), y + panelOrigin.getY())) {
                    DungeonTile tile = area.selectTile(x, y, panelOrigin);
                    if (!tile.isWasSeenByPlayer()) {
                        draw(g, x, y);
                    } else {

                        switch (tile.getTileType()) {
                            case OpenedDoor:
                                drawImage(g, x, y, TileImages.getImageFor(Empty));
                            case Wall:
                            case Entry:
                            case Empty:
                            case ClosedDoor:
                            default:
                                drawImage(g, x, y, TileImages.getImageFor(tile.getTileType()));
                        }

                        if (area.selectContentAt(x, y, panelOrigin) != null) {
                            Iterator<Item> i = tile.getTileContent().iterator();
                            while (i.hasNext()) {
                                Item item = i.next();
                                if (item instanceof ItemContainer) {
                                    drawImage(g, x, y, images.getChestImg());
                                }
                            }
                        }
                    }
                } else {
                    draw(g, x, y);
                }
            }

            drawPlayer(g);
        }
    }

    private void updateFogOfWar(int distance) {
        area.selectTile(playerScreenCoord.getX(), playerScreenCoord.getY(), panelOrigin).setWasSeenByPlayer(true);
        // walls block vision
        // left
        for (int i = 1; i < distance + 1; i++) {
            if (isInPlay(-i, 0)) {
                DungeonTile tile = area.selectTile(playerScreenCoord.getX() - i, playerScreenCoord.getY(), panelOrigin);
                tile.setWasSeenByPlayer(true);
                if (tile.getTileType().equals(Wall)) {
                    break;
                }
            }
        }
        // right
        for (int i = 1; i < distance + 1; i++) {
            if (isInPlay(i, 0)) {
                DungeonTile tile = area.selectTile(playerScreenCoord.getX() + i, playerScreenCoord.getY(), panelOrigin);
                tile.setWasSeenByPlayer(true);
                if (tile.getTileType().equals(Wall)) {
                    break;
                }
            }
        }

        // up
        for (int i = 1; i < distance + 1; i++) {
            if (isInPlay(0, -i)) {
                DungeonTile tile = area.selectTile(playerScreenCoord.getX(), playerScreenCoord.getY() - i, panelOrigin);
                tile.setWasSeenByPlayer(true);
                if (tile.getTileType().equals(Wall)) {
                    break;
                }
            }
        }

        // down
        for (int i = 1; i < distance + 1; i++) {
            if (isInPlay(0, i)) {
                DungeonTile tile = area.selectTile(playerScreenCoord.getX(), playerScreenCoord.getY() + i, panelOrigin);
                tile.setWasSeenByPlayer(true);
                if (tile.getTileType().equals(Wall)) {
                    break;
                }
            }
        }

        // left up
        for (int i = 1; i < distance + 1; i++) {
            if (isInPlay(-i, -i)) {
                DungeonTile tile = area.selectTile(playerScreenCoord.getX() - i, playerScreenCoord.getY() - i, panelOrigin);
                tile.setWasSeenByPlayer(true);
                if (tile.getTileType().equals(Wall)) {
                    break;
                }
            }
        }

        // right up
        for (int i = 1; i < distance + 1; i++) {
            if (isInPlay(i, -i)) {
                DungeonTile tile = area.selectTile(playerScreenCoord.getX() + i, playerScreenCoord.getY() - i, panelOrigin);
                tile.setWasSeenByPlayer(true);
                if (tile.getTileType().equals(Wall)) {
                    break;
                }
            }
        }

        // left down
        for (int i = 1; i < distance + 1; i++) {
            if (isInPlay(-i, i)) {
                DungeonTile tile = area.selectTile(playerScreenCoord.getX() - i, playerScreenCoord.getY() + i, panelOrigin);
                tile.setWasSeenByPlayer(true);
                if (tile.getTileType().equals(Wall)) {
                    break;
                }
            }
        }

        // right down
        for (int i = 1; i < distance + 1; i++) {
            if (isInPlay(i, i)) {
                DungeonTile tile = area.selectTile(playerScreenCoord.getX() + i, playerScreenCoord.getY() + i, panelOrigin);
                tile.setWasSeenByPlayer(true);
                if (tile.getTileType().equals(Wall)) {
                    break;
                }
            }
        }

        // change origin if number of tiles to visible edge < =distance
        if (visibleWidth - playerScreenCoord.getX() <= distance) {
            int newX = panelOrigin.getX() + 1;
            if (newX < area.getWidth()) {
                panelOrigin.setX(newX);
                playerScreenCoord.setX(playerScreenCoord.getX() - 1);
            }
        } else if (visibleWidth - playerScreenCoord.getX() >= visibleWidth - distance) {
            int newX = panelOrigin.getX() - 1;
            if (newX >= 0) {
                panelOrigin.setX(newX);
                playerScreenCoord.setX(playerScreenCoord.getX() + 1);
            }
        } else if (visibleHeight - playerScreenCoord.getY() <= distance) {
            int newY = panelOrigin.getY() + 1;
            if (newY < area.getHeight()) {
                panelOrigin.setY(newY);
                playerScreenCoord.setY(playerScreenCoord.getY() - 1);
            }
        } else if (visibleHeight - playerScreenCoord.getY() >= visibleHeight - distance) {
            int newY = panelOrigin.getY() - 1;
            if (newY >= 0) {
                panelOrigin.setY(newY);
                playerScreenCoord.setY(playerScreenCoord.getY() + 1);
            }
        }
    }

    private boolean isInPlay(int x, int y) {
        return (playerScreenCoord.getX() + x + panelOrigin.getX() >= 0 && playerScreenCoord.getY() + y + panelOrigin.getY() >= 0
                && playerScreenCoord.getX() + x + panelOrigin.getX() < area.getWidth() && playerScreenCoord.getY() + y + panelOrigin.getY() < area.getHeight());
    }

    private void drawPlayer(Graphics g) {
        g.setColor(Color.BLUE);
        drawImage(g, playerScreenCoord.getX(), playerScreenCoord.getY(), images.getHumanImg());
    }

    private void draw(Graphics g, int x, int y) {
        g.setColor(Color.BLACK);
        g.fillRect(x * defaultBlockSize, y * defaultBlockSize, defaultBlockSize, defaultBlockSize);
    }

    private void drawImage(Graphics g, int x, int y, Image image) {
        g.drawImage(image, x * defaultBlockSize, y * defaultBlockSize, 50, 50, this);
    }

    public void drawImageWithRealCoordinates(Coordinates coordinates, BufferedImage image) {
        // check if in visible area
        if (coordinates.getX() - panelOrigin.getX() >= 0
                && coordinates.getY() - panelOrigin.getY() >= 0
                && coordinates.getX() - panelOrigin.getX() - visibleWidth <= 0
                && coordinates.getY() - panelOrigin.getY() - visibleHeight <= 0) {
            this.paintComponent(this.getGraphics());
            drawImage(this.getGraphics(), coordinates.getX() - panelOrigin.getX(), coordinates.getY() - panelOrigin.getY(), image);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class UpActionController extends AbstractAction {
        DungeonPanel panel;

        private UpActionController(DungeonPanel panel) {
            this.panel = panel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int newY = panel.playerScreenCoord.getY() - 1;
            if (newY + panelOrigin.getY() >= 0
                    && panel.area.selectTile(panel.playerScreenCoord.getX(), newY, panel.panelOrigin).isWalkable()) {
                panel.playerScreenCoord.setY(newY);
                panel.repaint();
                panel.postMovementTriggers();
            }

        }
    }

    private void postMovementTriggers() {
        Trigger trigger = area.getTiles()[playerScreenCoord.getX()][playerScreenCoord.getY()].getTrigger();
        if (trigger == null) return;

//        for(Trigger t:triggers){
        if (trigger.getWhen().equals(TriggerTiming.PostMovement)) {
            trigger.getEffect().triggerEffect(area, this, app);
        }
//        }
    }

    private class DownActionController extends UpActionController {
        private DownActionController(DungeonPanel panel) {
            super(panel);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int newY = panel.playerScreenCoord.getY() + 1;
            if (newY + panelOrigin.getY() < panel.area.getHeight()
                    && panel.area.selectTile(panel.playerScreenCoord.getX(), newY, panelOrigin).isWalkable()) {
                panel.playerScreenCoord.setY(newY);
                panel.repaint();
                panel.postMovementTriggers();
            }
        }
    }

    private class LeftActionController extends UpActionController {
        private LeftActionController(DungeonPanel panel) {
            super(panel);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int newX = panel.playerScreenCoord.getX() - 1;
            if (newX + panelOrigin.getX() >= 0
                    && panel.area.selectTile(newX, panel.playerScreenCoord.getY(), panelOrigin).isWalkable()) {
                panel.playerScreenCoord.setX(newX);
                panel.repaint();
                panel.postMovementTriggers();
            }
        }
    }

    private class RightActionController extends UpActionController {
        private RightActionController(DungeonPanel panel) {
            super(panel);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int newX = panel.playerScreenCoord.getX() + 1;
            if (newX + panelOrigin.getX() < panel.area.getWidth()
                    && panel.area.selectTile(newX, panel.playerScreenCoord.getY(), panelOrigin).isWalkable()) {
                panel.playerScreenCoord.setX(newX);
                panel.repaint();
                panel.postMovementTriggers();
            }
        }
    }

    private class DungeonMouseListener implements MouseListener {
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

            LinkedList<Item> items = area.selectContentAt(dungeonX, dungeonY);
            Optional<Item> c = items.stream().filter(p -> p instanceof ItemContainer).findFirst();
            if (c.isPresent() && proximityChecker(dungeonX, dungeonY, playerScreenCoord.getX(), playerScreenCoord.getY())) {
                ItemContainerView v = new ItemContainerView((ItemContainer) c.get());
                v.mouseReleased(e);
            }

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        private boolean proximityChecker(int objX, int objY, int charX, int charY) {
            return Math.abs(objX - charX) <= 1 && Math.abs(objY - charY) <= 1;
        }
    }
}
