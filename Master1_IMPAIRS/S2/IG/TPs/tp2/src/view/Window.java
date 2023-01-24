package view;

import javax.swing.JFrame;

public class Window extends JFrame {
    public Window() {
        super("My Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);
    }
}