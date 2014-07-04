package seb.guild.model.dungeon;

import com.fasterxml.jackson.databind.ObjectMapper;
import seb.guild.model.item.Item;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public class DungeonArea {
    private String name;
    private int width;
    private int height;
    /* 0,0 is top left corner
    * all layout defaulted to wall */
    private DungeonTile[][] tiles;
    private String[] rumours;
    private Coordinates entryPoint;

    public DungeonArea() {
    }

    public DungeonArea(String name, int width, int height, DungeonTile[][] tiles, String[] rumours, Coordinates entryPoint) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.tiles = tiles;
        this.rumours = rumours;
        this.entryPoint = entryPoint;
    }

    public DungeonArea(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;
        tiles = new DungeonTile[width][height];
        for (int i = 0; i < height; i++) {
            fillLineWith(i, DungeonLayout.Wall);
        }
    }

    public void fillLineWith(int y, DungeonLayout filler) {
        for (int i = 0; i < width; i++) {
            this.tiles[i][y] = DungeonTile.get(filler);
        }
    }

    public void setSectionLine(int x, int y, DungeonTile... filler) {
        for (int i = x; i < x + filler.length; i++) {
            this.tiles[i][y] = filler[i - x];
        }
    }

    public void setLine(int y, DungeonTile... content) {
        for (int i = 0; i < content.length; i++) {
            this.tiles[i][y] = content[i];
        }
    }

    public String getName() {
        return name;
    }

    public String[] getRumours() {
        return rumours;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void addContent(int x, int y, Item item) {
        // TODO check if empty layout ?
        if (tiles[x][y] == null) {
            tiles[x][y] = new DungeonTile(DungeonLayout.Empty);
        }
        tiles[x][y].getTileContent().add(item);

    }

    public LinkedList<Item> selectContentAt(int x, int y) {
        return tiles[x][y].getTileContent();
    }

    public LinkedList<Item> selectContentAt(int x, int y, Coordinates origin) {
        return tiles[x+origin.getX()][y+origin.getY()].getTileContent();
    }

    @Override
    public String toString() {
        String s = "DungeonArea{" +
                "name='" + name + '\'' +
                ", width=" + width +
                ", height=" + height + "\n";

        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < width; x++) {
                s = s + tiles[x][y].getTileType().getStringView();
            }
            s = s + "\n";
        }

        s = s + ", rumours=" + Arrays.toString(rumours) +
                '}';

        return s;
    }
    public DungeonTile selectTile(Coordinates coord){
        return getTiles()[coord.getX()][coord.getY()];
    }
    public void setEntryPoint(Coordinates entryPoint) {
        this.entryPoint = entryPoint;
    }

    public Coordinates getEntryPoint() {
        return entryPoint;
    }

    public DungeonTile[][] getTiles() {
        return tiles;
    }
    public DungeonTile selectTile(int x, int y, Coordinates origin) {
        return tiles[x+origin.getX()][y+origin.getY()];
    }

    public boolean isInArea(int x, int y) {
        return ( x >= 0 &&  y >= 0
                &&  x < getWidth() && y < getHeight());
    }

    public void save(File f) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enableDefaultTyping();

        try {
            mapper.writeValue(f, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
