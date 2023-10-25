package main.java.commands.in.tcp;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.lang.Integer;

import main.java.client.ClientTCP;
import main.java.commands.CommandTCP;
import main.java.console.DebugLogger;
import main.java.console.DebugType;
import main.java.game.GameInfo;
import main.java.game.Game;

// OGAME m s***

public class CommandRcvTcpGameInfo extends CommandTCP{

    public CommandRcvTcpGameInfo(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP client, LinkedList<Integer> command) {
        
        DebugLogger.print(DebugType.CONFIRM, "COMMAND : OGAME");
        
        if (command.size() < 12) {
            DebugLogger.print(DebugType.WARNING, "[CommandRcvTcpGameInfo/WARNING] : les informations données par le serveur sont incomplétes, cette commande sera ignorée");
            return;
        }

        // read "m" : game id
        int m = command.get(6);
    
        // read "s" : nbr players in game m
        int s = command.get(8);

        try {
            GameInfo.games.get(m).setNbrPlayers(s);
        } catch (IndexOutOfBoundsException e) {
            GameInfo.games.add(new Game(s));
        }

        DebugLogger.print(DebugType.COM, "SERVER : OGAME " + m + " " + s);

    }

    @Override
    public void execute(ClientTCP clientTCP, String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
