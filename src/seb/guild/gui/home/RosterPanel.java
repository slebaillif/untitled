package seb.guild.gui.home;

import seb.guild.model.character.BaseCharacter;
import seb.guild.model.guild.Roster;

import javax.swing.*;
import java.awt.*;

public class RosterPanel extends JPanel{
    private Roster roster;
    private JPanel[] rosterItems;

    public RosterPanel(Roster roster) {
        this.roster = roster;
        this.rosterItems = new JPanel[roster.getCharacters().size()];
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        createRosterItems();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        createRosterItems();
    }

    private void createRosterItems() {
        int i = 0;
        for (BaseCharacter c:roster.getCharacters()){
            rosterItems[i] = new JPanel();
            JLabel name = new JLabel(c.getName());
            name.setVisible(true);
            rosterItems[i].add(name);
            this.add(rosterItems[i]);
            i++;
        }
    }
}
