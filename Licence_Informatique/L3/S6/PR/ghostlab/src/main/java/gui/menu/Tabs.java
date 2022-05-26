package main.java.gui.menu;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Tabs extends JTabbedPane {

    public Tabs(int width, int height) {
        setSize(width, height);
    }

    public void addTab(String title, JPanel panel) {
        this.addTab(title, panel);
    }

}

