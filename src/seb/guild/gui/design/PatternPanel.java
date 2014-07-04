package seb.guild.gui.design;

import seb.guild.model.dungeon.DungeonLayout;
import seb.guild.gui.TileImages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static seb.guild.model.dungeon.DungeonLayout.*;

public class PatternPanel extends JPanel {
    DesignModel model;

    public PatternPanel(DesignModel model) {
        this.model = model;
        setPreferredSize(new Dimension(5 * 50, 5 * 50));
        setBackground(Color.gray);
        setBorder(BorderFactory.createLineBorder(Color.black));

        createButton(Empty);
        createButton(Wall);
        createButton(Entry);
        createButton(ClosedDoor);
        createButton(OpenedDoor);
        createButton(Select);
        createButton(StairsDown);
        createButton(StairsUp);
    }

    public void createButton(DungeonLayout layout) {
        JButton empty = new JButton(new ImageIcon(TileImages.getImageFor(layout)));
        empty.setVisible(true);
        empty.addActionListener(new PatternSetterActionListener(layout));
        this.add(empty, BorderLayout.CENTER);

    }

    class PatternSetterActionListener implements ActionListener {
        DungeonLayout layout;

        PatternSetterActionListener(DungeonLayout layout) {
            this.layout = layout;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            model.setSelectedPattern(layout);
        }
    }

}
