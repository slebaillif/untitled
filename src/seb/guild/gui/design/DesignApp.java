package seb.guild.gui.design;


import com.fasterxml.jackson.databind.ObjectMapper;
import seb.guild.model.dungeon.DungeonArea;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class DesignApp extends JFrame {
    public static void main(String[] args) {
        DesignApp app = new DesignApp();
        app.setTitle("The Design Guild");
        app.initUI();
    }

    void initUI() {

        DesignModel model = new DesignModel();
        DungeonArea area = new DungeonArea("Test", 20, 20);
        model.setArea(area);

        JScrollPane scrollPane = new JScrollPane(new DesignPanel(model));
        JPanel containerEast = new JPanel();
        containerEast.setPreferredSize(new Dimension(5 * 50, 15 * 50));
        containerEast.setBackground(Color.gray);
        containerEast.setBorder(BorderFactory.createLineBorder(Color.black));

        PatternPanel patternPanel = new PatternPanel(model);
        TriggerPanel triggerPanel = new TriggerPanel(model);
        DetailsPanel detailsPanel = new DetailsPanel(model);
        containerEast.add(patternPanel,BorderLayout.NORTH);
        containerEast.add(triggerPanel,BorderLayout.CENTER);
        containerEast.add(detailsPanel,BorderLayout.SOUTH);

        this.getContentPane().add(scrollPane, BorderLayout.CENTER);
        this.getContentPane().add(containerEast, BorderLayout.EAST);
        int visibleWidth = 15;
        int visibleHeight = 15;
        int defaultBlockSize = 50;
        this.setSize(new Dimension(visibleWidth * defaultBlockSize, visibleHeight * defaultBlockSize));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);


        //Create the menu bar.

//        menu.addActionListener(new OpenActionListener());

        MenuBar b = new MenuBar();
        Menu m = new Menu("File");
        b.add(m);
        MenuItem open = new MenuItem("Open");
        MenuItem save = new MenuItem("Save");
        MenuItem newItem = new MenuItem("New");
        m.add(newItem);
        m.add(open);
        m.add(save);
        setMenuBar(b);
        open.addActionListener(new OpenActionListener(model));
        save.addActionListener(new SaveActionListener(model));
        newItem.addActionListener(new NewActionListener());
    }

    private class OpenActionListener implements ActionListener {
        DesignModel model;

        private OpenActionListener(DesignModel model) {
            this.model = model;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser openFile = new JFileChooser();
            int result = openFile.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                ObjectMapper mapper = new ObjectMapper();
                mapper.enableDefaultTyping();
                try {
                    DungeonArea a = mapper.readValue(openFile.getSelectedFile(), DungeonArea.class);
                    model.setArea(a);
                    repaint();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

    }

    private class SaveActionListener implements ActionListener {
        DesignModel model;

        private SaveActionListener(DesignModel model) {
            this.model = model;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser saveFile = new JFileChooser();
            int result = saveFile.showSaveDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File f = saveFile.getSelectedFile();
                model.getArea().save(f);

            }
        }


    }

    private class NewActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
