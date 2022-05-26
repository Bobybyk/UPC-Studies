package main.java.commands.in.tcp;

import java.io.PrintWriter;
import java.util.LinkedList;

import main.java.client.ClientTCP;
import main.java.commands.CommandTCP;
import main.java.console.DebugLogger;
import main.java.console.DebugType;
import main.java.game.GameInfo;

/// REGOK m***

public class CommandRcvTcpJoinOK extends CommandTCP{

    public CommandRcvTcpJoinOK(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP client, LinkedList<Integer> command) {

        DebugLogger.print(DebugType.CONFIRM, "COMMAND : REGOK");

        if (command.size() < 10) {
            DebugLogger.print(DebugType.WARNING, "[CommandRcvTcpJoinOK/WARNING] : les informations données par le serveur sont incomplétes, cette commande sera ignorée");
            return;
        }

        int gameId = command.get(6);

        GameInfo.isInGame = true;
        GameInfo.registredGameId = gameId;

        DebugLogger.print(DebugType.COM, "SERVER : REGOK " + gameId); 
    
    }

    @Override
    public void execute(ClientTCP clientTCP, String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
