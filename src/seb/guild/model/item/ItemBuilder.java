package seb.guild.model.item;

public class ItemBuilder {

    public ItemContainer getWoodenBox(){
        ItemContainer c = new ItemContainer("Wooden box", "container", "", 6, true);
        return c;
    }
}
