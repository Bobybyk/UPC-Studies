package main.java.commands.in.tcp;

import java.io.PrintWriter;
import java.util.LinkedList;

import main.java.client.ClientTCP;
import main.java.commands.CommandTCP;
import main.java.console.DebugLogger;
import main.java.console.DebugType;
import main.java.game.GameInfo;
import main.java.game.Player;

// MOVE! x y***

public class CommandRcvTcpNewPos extends CommandTCP {

    public CommandRcvTcpNewPos(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP clientTCP, LinkedList<Integer> command) {
        DebugLogger.print(DebugType.CONFIRM, "COMMAND : MOVE!");

        if (command.size() < 16) {
            DebugLogger.print(DebugType.WARNING, "[CommandRcvTcpNewPos/WARNING] : les informations données par le serveur sont incomplétes, cette commande sera ignorée");
            return;
        }

        String x = "";
        String y = "";
        
        for (int i = 6 ; i < 9 ; i++) {
            x += (char) command.get(i).byteValue();
        }
        for (int i = 10 ; i < 13 ; i++) {
            y += (char) command.get(i).byteValue();
        }

        try {
            GameInfo.players.get(GameInfo.playerID).setLastPosX(GameInfo.players.get(GameInfo.playerID).getPosX());
            GameInfo.players.get(GameInfo.playerID).setLastPosY(GameInfo.players.get(GameInfo.playerID).getPosY());
            GameInfo.players.get(GameInfo.playerID).setPosX(Integer.parseInt(x));
            GameInfo.players.get(GameInfo.playerID).setPosY(Integer.parseInt(y));
        } catch (NumberFormatException e) {
            DebugLogger.print(DebugType.WARNING, "[CommandRcvTcpNewPos/WARNING] : les informations de coordonnées du joueur n'ont pas été correctement données par le serveur, cette commande sera ignorée");
            return;
        }

        DebugLogger.print(DebugType.COM, "SERVER : MOVE! " + x + " " + y);
    }

    @Override
    public void execute(ClientTCP clientTCP, String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
