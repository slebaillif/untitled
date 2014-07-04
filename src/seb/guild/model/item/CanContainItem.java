package seb.guild.model.item;

import java.util.List;

public interface CanContainItem {
    List<Item> getContent();
    void addItem(Item item);
    void removeItem(Item item);
}
