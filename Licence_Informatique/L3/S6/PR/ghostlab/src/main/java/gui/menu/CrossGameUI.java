package main.java.gui.menu;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CrossGameUI extends JPanel {
    private JButton up;
    private JButton down;
    private JButton left;
    private JButton right;
    private JTextField gap;

    public CrossGameUI() {
        up = new JButton("UP");
        down = new JButton("DOWN");
        left = new JButton("LEFT");
        right = new JButton("RIGHT");
        gap = new JTextField(5);

        GridLayout crossesLayout = new GridLayout(3, 3);
        this.setLayout(crossesLayout);

        this.add(new JLabel());
        this.add(up);
        this.add(new JLabel());

        this.add(left);
        this.add(gap);
        this.add(right);

        this.add(new JLabel());
        this.add(down);
        this.add(new JLabel());
    }

    public JButton getUp() {
        return up;
    }
    public JButton getDown() {
        return down;
    }
    public JButton getLeft() {
        return left;
    }
    public JButton getRight() {
        return right;
    }
    public JTextField getGap() {
        return gap;
    }
}
