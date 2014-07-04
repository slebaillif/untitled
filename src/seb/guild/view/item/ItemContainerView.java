package seb.guild.view.item;

import seb.guild.model.item.ItemContainer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ItemContainerView extends MouseAdapter implements ActionListener {
    ItemContainer itemContainer;

    JPopupMenu popupMenu;
    private final JMenuItem open;
    private final JMenuItem close;

    public ItemContainerView(ItemContainer itemContainer) {
        this.itemContainer = itemContainer;
        popupMenu = new JPopupMenu();
        open = new JMenuItem("Open");
        open.addActionListener(this);
        popupMenu.add(open);
        close = new JMenuItem("Close");
        close.addActionListener(this);
        popupMenu.add(close);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            popupMenu.show(e.getComponent(), e.getX(), e.getY());
        } else {
            super.mouseReleased(e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Open":
                itemContainer.open();
                popupMenu.hide();
                break;
            case "Close":
                itemContainer.close();
                popupMenu.hide();
                break;
        }
    }
}
