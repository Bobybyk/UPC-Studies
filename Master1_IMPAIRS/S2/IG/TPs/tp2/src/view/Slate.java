package view;

import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.Graphics;

public class Slate extends JPanel {
    private BufferedImage canvas;

    public Slate() {
        super();
        this.canvas = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.canvas, 0, 0, this);
        setPreferredSize(new Dimension(800, 600));
    }
}
