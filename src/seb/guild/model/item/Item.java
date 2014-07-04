package seb.guild.model.item;

public class Item {
    private String name;
    private String category;
    private String type;
    private boolean occupiesWholeTile;

    public Item(String name, String category, String type, boolean occupiesWholeTile) {
        this.name = name;
        this.category = category;
        this.type = type;
        this.occupiesWholeTile = occupiesWholeTile;
    }

    public boolean isOccupiesWholeTile() {
        return occupiesWholeTile;
    }
}
