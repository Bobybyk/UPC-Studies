package main.java.commands.out.tcp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import main.java.client.ClientTCP;
import main.java.commands.CommandFormatter;
import main.java.commands.CommandTCP;
import main.java.console.DebugLogger;
import main.java.console.DebugType;
import main.java.game.GameInfo;

// IQUIT***

public class CommandAskTcpDropGame extends CommandTCP {

    public CommandAskTcpDropGame(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP clientTCP, LinkedList<Integer> command) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void execute(ClientTCP clientTCP, String[] args) {
        DebugLogger.print(DebugType.CONFIRM, "COMMAND : IQUIT");

        if (!GameInfo.hasGameStarted) {
            DebugLogger.print(DebugType.ERROR, "[CommandAskTcpDropGame/WARNING] : le jeu n'a pas encore commencé, cette commande sera ignorée");
            return;
        }

        try {
            clientTCP.getOutputStream().write(CommandFormatter.formatForTCP(new String[] {"IQUIT"}));
            clientTCP.getOutputStream().flush();
            DebugLogger.print(DebugType.COM, "CLIENT : " + "IQUIT");
        } catch(IOException e) {
            e.printStackTrace();
        }
        
    }
    
}
