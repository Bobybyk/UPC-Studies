package main.java.gui;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import main.java.console.Console;
import main.java.console.DebugLogger;
import main.java.console.DebugType;
import main.java.game.GameInfo;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class GameManagerPanel extends JPanel {
    private LinkedList<JRadioButton> gameListSelector;
    private LinkedList<JButton> gameListRefreshButtons;
    private JButton joinButton;
    private JButton refreshButton;
    private JButton createButton;
    private JButton startButton;
    private JButton leaveButton;
    private ButtonGroup buttonGroup;

    public GameManagerPanel() {
        gameListSelector = new LinkedList<>();
        gameListRefreshButtons = new LinkedList<>();
        joinButton = new JButton("join game");
        createButton = new JButton("new game");
        refreshButton = new JButton("refresh list");
        startButton = new JButton("start game");
        leaveButton = new JButton("leave game");
        buttonGroup = new ButtonGroup();

        createButton.setEnabled(false);
        startButton.setEnabled(false);
        leaveButton.setEnabled(false);

        GridLayout gameManagerLayout = new GridLayout(2, 2);
        this.setLayout(gameManagerLayout);
        this.add(joinButton);
        this.add(createButton);
        this.add(refreshButton);
        this.add(startButton);
        this.add(leaveButton);
    }

    public void listGames() {

        freeGamesList();
        GridLayout gameManagerLayout = new GridLayout(GameInfo.games.size()+2, 3);
        this.setLayout(gameManagerLayout);

        for (int i = 0 ; i < GameInfo.games.size() ; i++) {
            JRadioButton selector = new JRadioButton();
            JButton button = new JButton("refresh game");
            gameListSelector.add(selector);
            gameListRefreshButtons.add(button);
            buttonGroup.add(selector);

            if (!GameInfo.games.isEmpty()) {

                if (GameInfo.games.get(i).getHeight() == -1 || GameInfo.games.get(i).getWidth() == -1) {
                    this.add(new JLabel("game n°" + i + ", joueurs inscrits : " + GameInfo.games.get(i).getNbrPlayers()));
                } else {
                    this.add(new JLabel("game n°" + i + ", joueurs inscrits : " + GameInfo.games.get(i).getNbrPlayers() + ", taille du labyrinthe : [" + GameInfo.games.get(i).getHeight() + ", " + GameInfo.games.get(i).getWidth() + "]"));
                }
                this.add(selector);
                this.add(button);
            }

            int m = i;
            button.addActionListener(new ActionListener(){
                
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!GameInfo.hasGameStarted) {
                        System.out.println("");
                        Console.useMessage("LIST? " + m);
                        System.out.println("");
                        Console.useMessage("SIZE? " + m);
                    } else {
                        DebugLogger.print(DebugType.WARNING, "[ATTENTION/GameManagerPanel] : Vous êtes en jeu, vous ne pouvez pas rafraichir les informations de cette partie");
                    }

                }
            });
        }
        this.add(joinButton);
        this.add(createButton);
        this.add(refreshButton);
        this.add(startButton);
        this.add(leaveButton);

        startButton.setEnabled(false);
        leaveButton.setEnabled(false);
    }

    public void freeGamesList() {
        gameListSelector.clear();
        gameListRefreshButtons.clear();
        this.removeAll();

        GridLayout gameManagerLayout = new GridLayout(2, 2);
        this.setLayout(gameManagerLayout);
        this.add(joinButton);
        this.add(createButton);
        this.add(refreshButton);
        this.add(startButton);
        this.add(leaveButton);
        startButton.setEnabled(false);
        leaveButton.setEnabled(false);
    }

    public JButton getJoinButton() {
        return joinButton;
    }
    public JButton getNewGameButton() {
        return createButton;
    }
    public JButton getRefreshButton() {
        return refreshButton;
    }
    public JButton getStartButton() {
        return startButton;
    }
    public JButton getLeaveButton() {
        return leaveButton;
    }
    public int getSelectedButtonID() {
        for (int i = 0 ; i < gameListSelector.size() ; i++) {
            if (gameListSelector.get(i).isSelected()) {
                return i;
            }
        }
        return -1;
    }

}
