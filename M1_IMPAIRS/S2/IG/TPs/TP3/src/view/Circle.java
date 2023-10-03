package view;

import javax.swing.JComponent;

import model.Parser;

import java.awt.Graphics;

public class Circle extends JComponent {
    private String value;
    private int x;
    private int y;

    public Circle(String value, int x, int y) {
        this.value = value;
        this.x = x;
        this.y = y;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawOval(x, y, Parser.getCircleRadius(), Parser.getCircleRadius());
        g.drawString(value, x + Parser.getCircleRadius()/4, y + Parser.getCircleRadius()/2);
    }
}
