package seb.guild.model.item;

import java.util.LinkedList;
import java.util.List;

public class ItemContainer extends Item implements CanContainItem {
    LinkedList<Item> content = new LinkedList<>();
    private int maxCapacity;
    // TODO usefull ? just use size of list ? depends on how multiples are managed eg arrows
    private int usedCapacity = 0;
    private boolean open = false;

    public ItemContainer(String name, String category, String type, int capacity, boolean occupyTile) {
        super(name, category, type, occupyTile);
        this.maxCapacity = capacity;
    }

    @Override
    public List<Item> getContent() {
        return content;
    }

    @Override
    public void addItem(Item item) {
        if (usedCapacity + 1 <= maxCapacity) {
            content.add(item);
            usedCapacity++;
        } else {
            throw new IllegalArgumentException("Cannot add item. Container full");
        }
    }

    @Override
    public void removeItem(Item item) {
         if (content.remove(item)){
             usedCapacity--;
         } else {
             throw new IllegalArgumentException("Cannot remove item. Container did not contain:"+item);
         }
    }


    public void open() {
        this.open = true;
    }

    public void close() {
        this.open = false;
    }
}
