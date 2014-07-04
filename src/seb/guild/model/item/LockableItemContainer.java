package seb.guild.model.item;

public class LockableItemContainer extends ItemContainer implements CanBeLocked {
    private boolean isLocked = false;

    public LockableItemContainer(String name, String category, String type, int capacity, boolean occupyTile) {
        super(name, category, type, capacity, occupyTile);
    }

    @Override
    public boolean isLocked() {
        return isLocked;
    }

    @Override
    public void unlock() {
          isLocked = false;
    }

    @Override
    public void lock() {
        isLocked = true;
    }
}
