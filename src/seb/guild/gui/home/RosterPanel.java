package seb.guild.gui.home;

import seb.guild.model.character.BaseCharacter;
import seb.guild.model.guild.Roster;

import javax.swing.*;
import java.awt.*;

public class RosterPanel extends JPanel{
    private Roster roster;

    public RosterPanel(Roster roster) {
        this.roster = roster;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (BaseCharacter c:roster.getCharacters()){
            JLabel name = new JLabel(c.getName());
            name.setVisible(true);
            add(name);
        }
    }
}
