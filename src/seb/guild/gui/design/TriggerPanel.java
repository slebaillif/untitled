package seb.guild.gui.design;

import seb.guild.model.dungeon.DungeonLayout;
import seb.guild.model.effects.LeftFiringDartTrap;
import seb.guild.model.effects.MoveToAnotherMap;
import seb.guild.model.effects.OpenDoorEffect;
import seb.guild.model.triggers.Trigger;
import seb.guild.model.triggers.TriggerTiming;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TriggerPanel extends JPanel {
    DesignModel model;

    public TriggerPanel(DesignModel model) {
        this.model = model;

        setBackground(Color.gray);
        setBorder(BorderFactory.createLineBorder(Color.black));
        setPreferredSize(new Dimension(5 * 50, 5 * 50));

        JButton openDoorTriggerButton = new JButton("OpenDoorTrigger");
        openDoorTriggerButton.setVisible(true);
        add(openDoorTriggerButton, BorderLayout.CENTER);
        openDoorTriggerButton.addActionListener(new OpenDoorTriggerActionListener());

        JButton leftFiringDart = new JButton("LeftFiringDart");
        leftFiringDart.setVisible(true);
        add(leftFiringDart, BorderLayout.CENTER);
        leftFiringDart.addActionListener(new LeftFiringDartActionListener());

        JButton moveToOtherMap = new JButton("MoveToOtherMap");
        moveToOtherMap.setVisible(true);
        add(moveToOtherMap, BorderLayout.CENTER);
        moveToOtherMap.addActionListener(new MoveToOtherMapActionListener());


    }

    private class OpenDoorTriggerActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.setSelectedPattern(DungeonLayout.Trigger);
            model.setTriggerCoordinates(null);
            model.setTriggerTarget(null);
            model.setTrigger(new Trigger(TriggerTiming.PostMovement, new OpenDoorEffect()));
        }
    }

    private class LeftFiringDartActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.setSelectedPattern(DungeonLayout.Trigger);
            model.setTriggerCoordinates(null);
            model.setTriggerTarget(null);
            model.setTrigger(new Trigger(TriggerTiming.PostMovement, new LeftFiringDartTrap()));
        }
    }

    private class MoveToOtherMapActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.setSelectedPattern(DungeonLayout.Trigger);
            model.setTriggerCoordinates(null);
            model.setTriggerTarget(null);
            model.setTrigger(new Trigger(TriggerTiming.PostMovement, new MoveToAnotherMap()));
        }
    }
}
