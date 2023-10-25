package main.java.gui.game;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Image;
import java.util.HashMap;

import main.java.game.GameInfo;
import main.java.game.Ghost;

import java.awt.GridLayout;

public class Labyrinthe extends JPanel {
    private JLabel[][] lab;
    private int nbrMoves;
    private int nbrGhostsShifting;
    private HashMap<Ghost, ImageIcon> lastBox;

    public Labyrinthe() {

        this.setLayout(new GridLayout(GameInfo.gameHeight, GameInfo.gameWidth));
        this.lab = new JLabel[GameInfo.gameHeight][GameInfo.gameWidth];
        this.nbrMoves = 0;
        this.nbrGhostsShifting = 0;

        this.lastBox = new HashMap<>();

        for (int i = 0; i < GameInfo.gameHeight; i++) {
            for (int j = 0; j < GameInfo.gameWidth; j++) {
                JLabel img = new JLabel();
                this.add(img);
                lab[i][j] = img;
                img.setIcon(new ImageIcon(Box.VOID.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
            }
        }
        lab[GameInfo.players.get(GameInfo.playerID).getPosX()][GameInfo.players.get(GameInfo.playerID).getPosY()].setIcon(new ImageIcon(Box.PLAYER.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));

    }

    // actualisation du labyrinthe
    public void actualise() {

        int posX = GameInfo.players.get(GameInfo.playerID).getPosX();
        int posY = GameInfo.players.get(GameInfo.playerID).getPosY();
        int lastPosX = GameInfo.players.get(GameInfo.playerID).getLastPosX();
        int lastPosY = GameInfo.players.get(GameInfo.playerID).getLastPosY();
        int shiftingAsked = GameInfo.players.get(GameInfo.playerID).getShiftingAsked();

        // Si ces champs n'ont pas été initialisés, on ne cherche pas à afficher
        if (posX == -1 || posY == -1 || lastPosX == -1 || lastPosY == -1 || shiftingAsked == -1 || GameInfo.players.isEmpty()) {
            return;
        }

        // suppression des fantômes précédemment affichés
        for (HashMap.Entry<Ghost, ImageIcon> g : lastBox.entrySet()) {
            lab[g.getKey().getPosX()][g.getKey().getPosY()].setIcon(g.getValue());
        }

        lastBox.clear();
        
        // ajout des nouveau fantômes à ajouter dans la liste
        for (int i = nbrGhostsShifting ; i < GameInfo.ghosts.size() ; i++) {
                int ghostPosX = GameInfo.ghosts.get(i).getPosX();
                int ghostPosY = GameInfo.ghosts.get(i).getPosY();

                lastBox.put(GameInfo.ghosts.get(i), (ImageIcon) lab[ghostPosX][ghostPosY].getIcon());

                lab[ghostPosX][ghostPosY].setIcon(new ImageIcon(Box.GHOST.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                nbrGhostsShifting++;
        }

        if (GameInfo.nbrMoves != nbrMoves) {
            nbrMoves++;

            // S'est-on déplace en X ? (verticalement)
            if (posX != lastPosX) {

                // S'est-on déplacé vers le bas ?
                if (lastPosX < posX) {

                    // A-t-on rencontré un mur en bas ?
                    if (posX - lastPosX != shiftingAsked) {
                        if (posX+1 < GameInfo.gameHeight) {
                            lab[posX+1][posY].setIcon(new ImageIcon(Box.WALL.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                        }
                    }
                    

                    // Pavage des cases où on s'est déplacé
                    for (int i = lastPosX ; i <= posX ; i++) {
                        lab[i][posY].setIcon(new ImageIcon(Box.PATH.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                    }
                }

                // S'est-on déplacé vers le haut ?
                else if (lastPosX > posX) {

                    // A-t-on rencontré un mur le haut ?
                    if (lastPosX - posX != shiftingAsked) {
                        if (posX-1 >= 0) {
                            lab[posX-1][posY].setIcon(new ImageIcon(Box.WALL.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                        }
                    }

                    // Pavage des cases où on s'est déplacé
                    for (int i = lastPosX ; i >= posX ; i--) {
                        lab[i][posY].setIcon(new ImageIcon(Box.PATH.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                    }
                }

                // la dernière position en X est maintenant la position courante
                GameInfo.players.get(GameInfo.playerID).setLastPosX(posX);
            }

            // S'est-on déplace en Y ? (horizontalement)
            else if (posY != lastPosY) {

                // S'est-on déplacé vers la droite ?
                if (lastPosY < posY) {

                    // A-t-on rencontré un mur à droite ?
                    if (posY - lastPosY != shiftingAsked) {
                        if (posY+1 < GameInfo.gameWidth) {
                            lab[posX][posY+1].setIcon(new ImageIcon(Box.WALL.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                        }
                        
                    }

                    // Pavage des cases où on s'est déplacé
                    for (int i = lastPosY; i <= posY; i++) {
                        lab[posX][i].setIcon(new ImageIcon(Box.PATH.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                    }
                }

                // S'est-on déplacé vers à gauche ?
                else if (lastPosY > posY) {

                    // A-t-on rencontré un mur à gauche ?
                    if (lastPosY - posY != shiftingAsked) {
                        if (posY-1 > 0) {
                            lab[posX][posY-1].setIcon(new ImageIcon(Box.WALL.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                        }
                    }

                    // Pavage des cases où on s'est déplacé
                    for (int i = lastPosY; i >= posY; i--) {
                        lab[posX][i].setIcon(new ImageIcon(Box.PATH.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                    }
                }

                // la dernière position en Y est maintenant la position courante
                GameInfo.players.get(GameInfo.playerID).setLastPosY(posY);
            }

            // drwate
            else if (GameInfo.lastMoveDirection == 0) {
                if (posY+1 < GameInfo.gameWidth) {
                    lab[posX][posY+1].setIcon(new ImageIcon(Box.WALL.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                }
            }

            // gôche
            else if (GameInfo.lastMoveDirection == 1) {
                if (posY-1 >= 0) {
                    lab[posX][posY-1].setIcon(new ImageIcon(Box.WALL.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                }
            }

            // bas
            else if (GameInfo.lastMoveDirection == 2) {
                if (posX+1 < GameInfo.gameHeight) {
                    lab[posX+1][posY].setIcon(new ImageIcon(Box.WALL.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                }
            }

            // haut
            else if (GameInfo.lastMoveDirection == 3) {
                if (posX-1 >= 0) {
                    lab[posX-1][posY].setIcon(new ImageIcon(Box.WALL.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                }
            }

            // affichage du joueur à sa nouvelle position
            lab[posX][posY].setIcon(new ImageIcon(Box.PLAYER.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        }

    }

}
