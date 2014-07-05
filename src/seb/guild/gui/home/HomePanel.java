package seb.guild.gui.home;

import seb.guild.gui.design.*;
import seb.guild.model.dungeon.DungeonArea;
import seb.guild.model.game.Game;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {
    private Game game;

    public HomePanel(Game game) {
        this.game = game;
    }

    void initUI() {
        RosterPanel rosterPanel = new RosterPanel(game.getRoster());
        ResourcePanel resourcePanel = new ResourcePanel();
        BuildingPanel buildingPanel = new BuildingPanel();

        JScrollPane scrollPane = new JScrollPane(rosterPanel);

        JPanel containerWest = new JPanel();
        containerWest.setPreferredSize(new Dimension(5 * 50, 15 * 50));
        containerWest.setBackground(Color.gray);
        containerWest.setBorder(BorderFactory.createLineBorder(Color.black));

        JPanel containerCenter = new JPanel();
//        containerCenter.setPreferredSize(new Dimension(5 * 50, 15 * 50));
        containerCenter.setBackground(Color.gray);
        containerCenter.setBorder(BorderFactory.createLineBorder(Color.black));

        containerCenter.add(resourcePanel, BorderLayout.NORTH);
        containerWest.add(buildingPanel, BorderLayout.CENTER);

        this.add(containerWest, BorderLayout.WEST);
        this.add(containerCenter, BorderLayout.CENTER);
    }

}
