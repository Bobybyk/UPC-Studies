package main.java.commands.in.tcp;

import java.io.PrintWriter;
import java.util.LinkedList;

import main.java.client.ClientTCP;
import main.java.commands.CommandTCP;
import main.java.console.DebugLogger;
import main.java.console.DebugType;
import main.java.game.GameInfo;

// GAMES n***

public class CommandRcvTcpNbrGames extends CommandTCP {

    public CommandRcvTcpNbrGames(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP client, LinkedList<Integer> command) {

        DebugLogger.print(DebugType.CONFIRM, "COMMAND : GAMES");
        
        if (command.size() < 10) {
            DebugLogger.print(DebugType.WARNING, "[CommandRcvTcpNbrGames/WARNING] : les informations données par le serveur sont incomplétes, cette commande sera ignorée");
            return;
        }

        // read "n" uint8
        GameInfo.nbrGames = command.get(6);

        DebugLogger.print(DebugType.COM, "SERVER : GAMES " + GameInfo.nbrGames);

    }

    @Override
    public void execute(ClientTCP clientTCP, String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
