package main.java.commands.in.tcp;

import java.io.PrintWriter;
import java.util.LinkedList;

import main.java.client.ClientTCP;
import main.java.commands.CommandTCP;
import main.java.console.DebugLogger;
import main.java.console.DebugType;
import main.java.game.GameInfo;

// GLIS! s***

public class CommandRcvTcpNbrPlayersInGame extends CommandTCP {

    public CommandRcvTcpNbrPlayersInGame(PrintWriter pw) {
        super(pw);

    }

    @Override
    public void execute(ClientTCP clientTCP, LinkedList<Integer> command) {
        DebugLogger.print(DebugType.CONFIRM, "COMMAND : GLIS!");

        if (command.size() < 10) {
            DebugLogger.print(DebugType.WARNING, "[CommandRcvTcpNbrPlayersInGame/WARNING] : les informations données par le serveur sont incomplétes, cette commande sera ignorée");
            return;
        }

        // read "s" uint8
        GameInfo.nbrPlayers = command.get(6);

        DebugLogger.print(DebugType.COM, "SERVER : GLIS! " + GameInfo.nbrPlayers);
        
    }

    @Override
    public void execute(ClientTCP clientTCP, String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
