package main.java.commands.in.tcp;

import java.io.PrintWriter;
import java.util.LinkedList;

import main.java.client.ClientTCP;
import main.java.commands.CommandTCP;
import main.java.console.DebugLogger;
import main.java.console.DebugType;
import main.java.game.GameInfo;

// UNROK m***

public class CommandRcvTcpUnregisterOK extends CommandTCP {

    public CommandRcvTcpUnregisterOK(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP client, LinkedList<Integer> command) {

        DebugLogger.print(DebugType.CONFIRM, "COMMAND : UNROK");

        if (command.size() < 10) {
            DebugLogger.print(DebugType.WARNING, "[CommandRcvTcpUnregisterOK/WARNING] : les informations données par le serveur sont incomplétes, cette commande sera ignorée");
            return;
        }

        // read "m" uint8
        int gameId = command.get(6);

        GameInfo.isInGame = false;
        GameInfo.registredGameId = -1;

        DebugLogger.print(DebugType.COM, "SERVER : UNROK " + gameId);
    }

    @Override
    public void execute(ClientTCP clientTCP, String[] args) {
        
    }
    
}
