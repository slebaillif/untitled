package seb.guild.model.item;

public interface CanBeLocked {
    boolean isLocked();
    void unlock();
    void lock();
}
