import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class ImageEditView extends JFrame {
    private static final long serialVersionUID = 1L;
    private JButton cutButton = new JButton("cut");
    private JButton undoButton = new JButton("undo");
    private JButton redoButton = new JButton("redo");
    private JMenuBar menu = new JMenuBar();
    ImagePane imagePane;
    ImageEditModel model;

    public ImageEditView(ImageEditModel model) {
        this.model = model;
        this.setTitle("Editeur"); // Titre de la fenêtre
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); // Méthode de fermeture
        this.setJMenuBar(this.menu);
        this.imagePane = new ImagePane();
        this.setContentPane(imagePane);
        this.menu.add(cutButton);
        this.menu.add(undoButton);
        this.menu.add(redoButton);
        cutButton.addActionListener(e -> { 
            this.model.saveCut(imagePane.selection.getRectangle());
            imagePane.repaint();
            undoButton.setEnabled(true);
            redoButton.setEnabled(true);
            cutButton.setEnabled(false);
        });
        undoButton.addActionListener(e -> { 
            if (this.model.undoManager.canUndo()) {
                this.model.undoManager.undo();
                repaint();
            }
        });
        redoButton.addActionListener(e -> {
            this.model.undoManager.redo();
            repaint();
        });
    } 

    class ImagePane extends JPanel {
        private static final long serialVersionUID = 1L;
        Selection selection = new Selection();

        public ImagePane() {
            setPreferredSize(new Dimension(model.getImage().getWidth(), model.getImage().getHeight()));
            addMouseListener(selection);
            addMouseMotionListener(selection);
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(model.getImage(), 0, 0, this);
            ((Graphics2D) g).draw(selection.getRectangle());

        }
        
        class Selection extends MouseAdapter implements MouseMotionListener {
            int x1, y1, x2, y2;

            public Rectangle getRectangle() {
                return new Rectangle(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2-x1), Math.abs(y1-y2));
            }

            public void mousePressed(MouseEvent event) {
               this.x1 = event.getX();
               this.y1 = event.getY();
               cutButton.setEnabled(false);
               repaint();
            }

            public void mouseDragged(MouseEvent event) {
                this.x2 = event.getX();
                this.y2 = event.getY();
                if (this.x2 != this.x1 && this.y2 != this.y1) {
                    cutButton.setEnabled(true);
                    repaint();
                }
            }

            public void mouseMoved (MouseEvent event) { }
        }
    }
}
