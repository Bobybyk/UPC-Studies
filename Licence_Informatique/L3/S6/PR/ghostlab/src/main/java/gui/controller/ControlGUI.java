package main.java.gui.controller;

import main.java.client.Client;
import main.java.console.Console;
import main.java.console.DebugLogger;
import main.java.console.DebugType;
import main.java.game.GameInfo;
import main.java.gui.Frame;
import main.java.gui.game.Box;

import java.awt.Color;

public class ControlGUI {
    private Frame frame;
    private Client client;
    private boolean labCreated = false;

    public ControlGUI(Frame frame) {
        this.frame = frame;
        frame.getConnectionPanel().getConnectionButton().addActionListener((event) -> connect());
        frame.getConnectionPanel().getDisconectionButton().addActionListener((event) -> disconnect());
        frame.getGameManagerPanel().getJoinButton().addActionListener((event) -> joinGame());
        frame.getGameManagerPanel().getNewGameButton().addActionListener((event) -> createNewGame());
        frame.getGameManagerPanel().getRefreshButton().addActionListener((event) -> refreshGamesList());
        frame.getGameManagerPanel().getStartButton().addActionListener((event) -> startGame());
        frame.getGameManagerPanel().getLeaveButton().addActionListener((event) -> leaveGame());
        frame.getExitButton().addActionListener((event) -> exit());
        frame.getHelpButton().addActionListener((event) -> help());
        frame.getCrossGameUIPanel().getUp().addActionListener((event -> moveUp()));
        frame.getCrossGameUIPanel().getDown().addActionListener((event -> moveDown()));
        frame.getCrossGameUIPanel().getLeft().addActionListener((event -> moveLeft()));
        frame.getCrossGameUIPanel().getRight().addActionListener((event -> moveRight()));

        frame.getConnectionPanel().getDisconectionButton().addChangeListener((event) -> actualise());

        actualise();
    }

    public void moveUp() {
        System.out.println("");
        Console.useMessage("UPMOV " + frame.getCrossGameUIPanel().getGap().getText());
    }
    public void moveDown() {
        System.out.println("");
        Console.useMessage("DOMOV " + frame.getCrossGameUIPanel().getGap().getText());
    }
    public void moveLeft() {
        System.out.println("");
        Console.useMessage("LEMOV " + frame.getCrossGameUIPanel().getGap().getText());
    }
    public void moveRight() {
        System.out.println("");
        Console.useMessage("RIMOV " + frame.getCrossGameUIPanel().getGap().getText());
    }

    private void exit() {
        System.out.println("");
        Console.useMessage("kill"); 
    }
    private void help() {
        System.out.println("");
        Console.useMessage("help"); 
    }

    private void startGame() {
        System.out.println("");
        Console.useMessage("START"); 
    }

    private void leaveGame() {
        System.out.println("");
        Console.useMessage("UNREG"); 
    }
    private void joinGame() {
        int id = frame.getGameManagerPanel().getSelectedButtonID();
        if (id != -1) {
            System.out.println("");
            Console.useMessage("REGIS " + id);
        } else {
            DebugLogger.print(DebugType.ERROR, "GUI : aucun bouton sélectionné");
        }
    }

    private void createNewGame() {
        System.out.println("");
        Console.useMessage("NEWPL");
    }

    private void refreshGamesList() {
        System.out.println("");
        Console.useMessage("GAME?");

    }

    public void actualise() {

        if (Client.isConnected) {
            frame.getConnectionPanel().getConnectionButton().setEnabled(false);
            frame.getConnectionPanel().getDisconectionButton().setEnabled(true);
            frame.getGameManagerPanel().listGames();
            frame.getGameManagerPanel().getJoinButton().setEnabled(true);

            if (GameInfo.isInGame && !GameInfo.hasGameStarted) {
                frame.getGameManagerPanel().getStartButton().setEnabled(true);
                frame.getGameManagerPanel().getLeaveButton().setEnabled(true);
                frame.getGameManagerPanel().getNewGameButton().setEnabled(false);
                frame.getGameManagerPanel().getJoinButton().setEnabled(false);
            }
            else if (GameInfo.hasGameStarted) {
                frame.getGameManagerPanel().getStartButton().setEnabled(false);
                frame.getGameManagerPanel().getLeaveButton().setEnabled(false);
                frame.getGameManagerPanel().getNewGameButton().setEnabled(false);
                frame.getGameManagerPanel().getJoinButton().setEnabled(false);
                frame.getGameManagerPanel().getRefreshButton().setEnabled(false);

                frame.getCrossGameUIPanel().getUp().setEnabled(true);
                frame.getCrossGameUIPanel().getDown().setEnabled(true);
                frame.getCrossGameUIPanel().getLeft().setEnabled(true);
                frame.getCrossGameUIPanel().getRight().setEnabled(true);
            }
            else {
                frame.getGameManagerPanel().getStartButton().setEnabled(false);
                frame.getGameManagerPanel().getLeaveButton().setEnabled(false);
                frame.getGameManagerPanel().getNewGameButton().setEnabled(true);
                frame.getGameManagerPanel().getJoinButton().setEnabled(true);
                frame.getGameManagerPanel().getRefreshButton().setEnabled(true);

                frame.getCrossGameUIPanel().getUp().setEnabled(false);
                frame.getCrossGameUIPanel().getDown().setEnabled(false);
                frame.getCrossGameUIPanel().getLeft().setEnabled(false);
                frame.getCrossGameUIPanel().getRight().setEnabled(false);
            }
        } else {
            frame.getConnectionPanel().getConnectionButton().setEnabled(true);
            frame.getConnectionPanel().getDisconectionButton().setEnabled(false);
            frame.getGameManagerPanel().getNewGameButton().setEnabled(false);
            frame.getGameManagerPanel().getJoinButton().setEnabled(false);
            frame.getGameManagerPanel().getStartButton().setEnabled(false);
            frame.getGameManagerPanel().getLeaveButton().setEnabled(false);
            frame.getGameManagerPanel().getRefreshButton().setEnabled(false);
            
            frame.getCrossGameUIPanel().getUp().setEnabled(false);
            frame.getCrossGameUIPanel().getDown().setEnabled(false);
            frame.getCrossGameUIPanel().getLeft().setEnabled(false);
            frame.getCrossGameUIPanel().getRight().setEnabled(false);

            frame.getGameManagerPanel().freeGamesList();
        }



        if (GameInfo.hasGameStarted && !labCreated) {
            Box.setTextures();
            frame.createLab();
            labCreated = true;
        }
        else if (GameInfo.hasGameStarted && labCreated) {
            frame.getLab().actualise();
            frame.getLab().updateUI();;
        }
        else if (!GameInfo.hasGameStarted && labCreated) {
            frame.freeLab();
            labCreated = false;
        }
        
        frame.getGameManagerPanel().updateUI();
        frame.getConnectionPanel().updateUI();
        frame.getConsolePanel().updateUI();

    }

    public void connect() {

        if (frame.getConnectionPanel().getIpField().getText().isEmpty() || frame.getConnectionPanel().getPortField().getText().isEmpty()) {
            frame.getConnectionPanel().setBackground(new Color(255, 102, 102));
            return;
        }

        try {
            client = new Client(frame.getConnectionPanel().getIpField().getText(), Integer.parseInt(frame.getConnectionPanel().getPortField().getText()), this);
        } catch (NumberFormatException e) {
            DebugLogger.print(DebugType.ERROR, "[ERREUR/ControlGUI] : le port doit représenter une valeur entière");
        }
        

        if (frame.getConnectionPanel().getplayerField().getText().isEmpty()) {
            GameInfo.playerID = "unknUser";
        } else {
            String playerId = "";

            for(int i = 0 ; i<8 ; i++) {
                if (i > frame.getConnectionPanel().getplayerField().getText().length() - 1) {
                    playerId += "x";
                } else {
                    playerId += frame.getConnectionPanel().getplayerField().getText().charAt(i);
                }
            }
            GameInfo.playerID = playerId;
        }

        if (Client.isConnected) {
            frame.getConnectionPanel().setBackground(new Color(178, 255, 102));
            frame.getConnectionPanel().getConnectionButton().setEnabled(false);
            frame.getConnectionPanel().getDisconectionButton().setEnabled(true);
            frame.getGameManagerPanel().getNewGameButton().setEnabled(true);
            frame.getGameManagerPanel().listGames();
            frame.repaint();
            Console.connectConsole(client.getClientTCP());
        } else {
            frame.getConnectionPanel().setBackground(new Color(255, 102, 102));
        }
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Frame getFrame() {
        return frame;
    }

    public void disconnect() {
        Client.disconnect();
        actualise();
    }
}
