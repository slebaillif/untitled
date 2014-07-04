package seb.guild.model.dungeon;

public enum DungeonLayout {
    Wall("*"),
    Empty(" "),
    SecretDoor("$"),
    Trap("T"),
    Water("~"),
    Lava("@"),
    ClosedDoor("d"),
    OpenedDoor("o"),
    Entry("E"),
    StairsUp("U"),
    StairsDown("D"),
    Trigger("t"),
    Select("");

    private String stringView;

    DungeonLayout(String stringView) {
        this.stringView = stringView;
    }

    public String getStringView() {
        return stringView;
    }
}
