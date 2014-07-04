package seb.guild.gui;

import seb.guild.model.dungeon.DungeonLayout;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static seb.guild.model.dungeon.DungeonLayout.*;

public class TileImages {
    private static TileImages instance;
    private Map<DungeonLayout, BufferedImage> mapLayoutImage;

    private final BufferedImage chestImg;

    private final BufferedImage humanImg;

    private final BufferedImage cursorRed;

    public static TileImages getInstance() {
        if (instance == null) {
            instance = new TileImages();
        }
        return instance;
    }

    public static BufferedImage getImageFor(DungeonLayout layout) {
        if (instance == null) {
            instance = new TileImages();
        }
        return instance.mapLayoutImage.get(layout);
    }

    private TileImages() {
        mapLayoutImage = new HashMap<>(30);

        try {
            mapLayoutImage.put(Wall, ImageIO.read(this.getClass().getClassLoader().getResource("dc-dngn/wall/brick_brown1.png")));
            mapLayoutImage.put(Empty, ImageIO.read(this.getClass().getClassLoader().getResource("dc-dngn/floor/floor_sand_stone0.png")));
            mapLayoutImage.put(Entry, ImageIO.read(this.getClass().getClassLoader().getResource("dc-dngn/gateways/dngn_entrance.png")));
            mapLayoutImage.put(StairsUp, ImageIO.read(this.getClass().getClassLoader().getResource("dc-dngn/gateways//stone_stairs_up.png")));
            mapLayoutImage.put(StairsDown, ImageIO.read(this.getClass().getClassLoader().getResource("dc-dngn/gateways//stone_stairs_down.png")));
            mapLayoutImage.put(Select, ImageIO.read(this.getClass().getClassLoader().getResource("dc-misc/cursor.png")));
            mapLayoutImage.put(ClosedDoor, ImageIO.read(this.getClass().getClassLoader().getResource("dc-dngn/dngn_closed_door.png")));
            mapLayoutImage.put(OpenedDoor, ImageIO.read(this.getClass().getClassLoader().getResource("dc-dngn/dngn_open_door.png")));

            chestImg = ImageIO.read(this.getClass().getClassLoader().getResource("UNUSED/other/chest2_closed.png"));
            humanImg = ImageIO.read(this.getClass().getClassLoader().getResource("dc-mon/human.png"));
            cursorRed = ImageIO.read(this.getClass().getClassLoader().getResource("dc-misc/cursor_red.png"));


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public BufferedImage getCursorRed() {
        return cursorRed;
    }

    public BufferedImage getChestImg() {
        return chestImg;
    }

    public BufferedImage getHumanImg() {
        return humanImg;
    }


}
