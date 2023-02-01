import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class View extends JPanel implements Observer{

    private BufferedImage imageTMP;

    public View() {
        this.setPreferredSize(new Dimension(600, 600));
        this.repaint();
    }

    @Override
    public void update(BufferedImage image) {
        this.imageTMP = image;
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(imageTMP, 0, 0, Color.CYAN, null);
    }
}