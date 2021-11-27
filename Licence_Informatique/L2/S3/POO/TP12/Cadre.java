import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import java.awt.Color;
import java.awt.event.MouseEvent;

/*

Veuillez m'excuser pour cette version non achevée du TP12.
J'ai avancé du mieux que je pouvais sur ce TP mais ai préféré
orienter mon temps de la semaine du 14 décembre sur les dernières révisions
pour les examens de décembre.
J'espère que vous trouverez dans mes précédents TP un meilleur pannel de mon travail.

Bonnes fêtes,

Matthieu Le Franc

*/

public class Cadre extends JFrame {
    private static final long serialVersionUID = 1L;
    JPanel pan;

    public Cadre() {

        // Définition du cadre et de l'objet carré
        this.setTitle("TP12");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.pan = new JPanel();
        this.pan.setLayout(null);
        this.getContentPane().add(this.pan);
        this.pan.add(new Carre());
    }

    public static void main(String[] args) {

        // Tentative de création du cadre
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Cadre c = new Cadre();
                c.setVisible(true);
            }
        });
    }

    public class Carre extends JPanel implements MouseInputListener {
        private static final long serialVersionUID = 1L;
        Modele mod;
        boolean move;

        //Création du carré
        public Carre() {
            this.setBounds(100, 200, 50, 50);
            // Création du modele de couleur pour le carré (aléatoire)
            this.mod = new Modele(1 + (int)(Math.random() * ((255 - 1) + 1)), 1 + (int)(Math.random() * ((255 - 1) + 1)), 1 + (int)(Math.random() * ((255 - 1) + 1)));
            this.setBackground(this.mod.color);
            this.addMouseListener(this);
            this.addMouseMotionListener(this);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            this.mod.color = this.getBackground();
            this.setBackground(Color.GREEN);

        }

        @Override
        public void mousePressed(MouseEvent e) {
            this.move = true;

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            this.move = false;

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseExited(MouseEvent e) {
            this.setBackground(this.mod.color);

        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if(move) {
                this.setLocation(Math.abs(this.getX()+e.getX()+e.getX()), Math.abs(this.getY()+e.getY()));
            }

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            // TODO Auto-generated method stub

        }
    }
}