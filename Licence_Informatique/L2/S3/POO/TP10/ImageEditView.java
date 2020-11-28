import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import org.w3c.dom.events.MouseEvent;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.Rectangle;

public class ImageEditView extends JFrame {
    private static final long serialVersionUID = 1L;
    private JButton cutButton;
    private JButton undoButton;
    private JButton redoButton;
    ImagePane imagePane;
    ImageEditModel model;

    class ImagePane extends JPanel {
        private static final long serialVersionUID = 1L;
        Selection selection = new Selection();

        public ImagePane() {
            setPreferredSize(new Dimension(model.getImage().getWidth(), model.getImage().getHeight()));
            addMouseListener((MouseListener) selection);
            addMouseMotionListener((MouseMotionListener) selection);
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(model.getImage(), 0, 0, this);
        }

        class Selection extends MouseAdapter implements MouseMotionListener {
            private int x, y, z, t;

            public Rectangle getRectangle() {
                return new Rectangle(x, y, z, t);
            }
            public void mousePressed(MouseEvent event) {
                
            }
        }
    }
}
