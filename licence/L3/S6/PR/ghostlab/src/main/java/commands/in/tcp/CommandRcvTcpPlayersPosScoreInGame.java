package main.java.commands.in.tcp;

import java.io.PrintWriter;
import java.util.LinkedList;

import main.java.client.ClientTCP;
import main.java.commands.CommandTCP;
import main.java.console.DebugLogger;
import main.java.console.DebugType;
import main.java.game.GameInfo;
import main.java.game.Player;

// GPLYR id x y p***

public class CommandRcvTcpPlayersPosScoreInGame extends CommandTCP {

    public CommandRcvTcpPlayersPosScoreInGame(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP clientTCP, LinkedList<Integer> command) {
        DebugLogger.print(DebugType.CONFIRM, "COMMAND : GPLYR");

        if (command.size() < 30) {
            DebugLogger.print(DebugType.ERROR, "[CommandRcvTcpPlayersPosScoreInGame/WARNING] : les informations données par le serveur sont incomplétes, cette commande sera ignorée");
            return;
        }

        int count = 0;
        String id = "";
        
        // read the id (8 char)
        while(count < 8) {
            id += (char) command.get(6 + count).byteValue();
            count++;
        }

        String x = "";
        String y = "";
        String points = "";
        
        for (int i = 6 ; i < 9 ; i++) {
            x += (char) command.get(i).byteValue();
        }
        for (int i = 9 ; i < 12 ; i++) {
            y += (char) command.get(i).byteValue();
        }
        for (int i = 13 ; i < 17 ; i++) {
            points += (char) command.get(i).byteValue();
        }

        int xValue = 0;
        int yValue = 0;
        int scoreValue = 0;

        try {
            xValue = Integer.parseInt(x);
            yValue = Integer.parseInt(y);
        } catch (NumberFormatException e) {
            DebugLogger.print(DebugType.ERROR, "[CommandRcvTcpPlayersPosScoreInGame/ERROR] : les informations de coordonnées du joueur n'ont pas été correctement données par le serveur, cette commande sera ignorée");
            return;
        }

        try {
            scoreValue = Integer.parseInt(points);
        } catch (NumberFormatException e) {
            DebugLogger.print(DebugType.ERROR, "[CommandRcvTcpPlayersPosScoreInGame/ERROR] : les informations de points données ne sont pas conformes, cette commande sera ignorée");
            return;
        }

        try {
            GameInfo.players.get(id);
        } catch (NullPointerException e) {
            GameInfo.players.put(id, new Player(scoreValue, xValue, yValue));
        }

        DebugLogger.print(DebugType.COM, "SERVER : GPLYR " + id + " " + x + " " + y + " " + points);

    }

    @Override
    public void execute(ClientTCP clientTCP, String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
