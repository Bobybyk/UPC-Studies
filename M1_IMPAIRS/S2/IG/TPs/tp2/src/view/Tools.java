package view;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Tools extends JPanel {
    private JButton crayon;
    private JButton pinceau;
    private JButton gomme;
    private JButton ligne;
    private JButton rectangle;
    private JButton cercle;
    private JButton remplir;

    public Tools() {
        super();
        this.setPreferredSize(new Dimension(100, 600));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        crayon = new JButton("crayon");
        pinceau = new JButton("pinceau");
        gomme = new JButton("gomme");
        ligne = new JButton("ligne");
        rectangle = new JButton("rectangle");
        cercle = new JButton("cercle");
        remplir = new JButton("remplir");

        this.add(crayon);
        this.add(pinceau);
        this.add(gomme);
        this.add(ligne);
        this.add(rectangle);
        this.add(cercle);
        this.add(remplir);
    }

    public JButton getCrayon() {
        return crayon;
    }
    public JButton getPinceau() {
        return pinceau;
    }
    public JButton getGomme() {
        return gomme;
    }
    public JButton getLigne() {
        return ligne;
    }
    public JButton getRectangle() {
        return rectangle;
    }
    public JButton getCercle() {
        return cercle;
    }
    public JButton getRemplir() {
        return remplir;
    }
}
