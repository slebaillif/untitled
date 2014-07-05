package seb.guild.gui;

import com.fasterxml.jackson.databind.ObjectMapper;
import seb.guild.gui.home.HomePanel;
import seb.guild.model.dungeon.DungeonArea;
import seb.guild.model.game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GuildApp extends JFrame {

    private DungeonArea area;
    private Game game;

    public GuildApp() throws HeadlessException {
    }

    public static void main(String[] args) {
        GuildApp app = new GuildApp();
        app.setTitle("The Guild");
        int visibleWidth = 15;
        int visibleHeight = 15;
        int defaultBlockSize = 50;
        app.setSize(new Dimension(visibleWidth * defaultBlockSize, visibleHeight * defaultBlockSize));
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);

        app.initUI();

    }

    private void initUI() {
        MenuBar b = new MenuBar();
        Menu m = new Menu("File");
        b.add(m);
        MenuItem open = new MenuItem("Open Dungeon");
        MenuItem newGame = new MenuItem("New Game");
        m.add(open);
        m.add(newGame);
        this.setMenuBar(b);
        open.addActionListener(new OpenActionListener(this));
        newGame.addActionListener(new NewGameActionListener(this));

    }

    public void setArea(DungeonArea area) {
        this.area = area;
    }

    private class OpenActionListener implements ActionListener {
        GuildApp app;

        private OpenActionListener(GuildApp app) {
            this.app = app;
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
                    app.setArea(a);
                    app.setVisible(false);
                    app.getContentPane().add(new DungeonPanel(a, app), BorderLayout.CENTER);
                    app.setVisible(true);
                    app.getContentPane().repaint();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

    }

    private class NewGameActionListener implements ActionListener {
        GuildApp app;

        public NewGameActionListener(GuildApp guildApp) {
            this.app = guildApp;

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            game = new Game();

            app.setVisible(false);
            app.getContentPane().add(new HomePanel(game), BorderLayout.CENTER);
//            app.setSize(new Dimension(visibleWidth * defaultBlockSize, visibleHeight * defaultBlockSize));
            app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            app.setVisible(true);
            app.getContentPane().repaint();
        }
    }
}
