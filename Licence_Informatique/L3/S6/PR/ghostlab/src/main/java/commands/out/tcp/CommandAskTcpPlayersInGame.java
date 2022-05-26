package main.java.commands.out.tcp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import main.java.client.ClientTCP;
import main.java.commands.CommandFormatter;
import main.java.commands.CommandTCP;
import main.java.console.DebugLogger;
import main.java.console.DebugType;

// GLIS?***

public class CommandAskTcpPlayersInGame extends CommandTCP {

    public CommandAskTcpPlayersInGame(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP clientTCP, LinkedList<Integer> command) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void execute(ClientTCP clientTCP, String[] args) {
        DebugLogger.print(DebugType.CONFIRM, "COMMAND : GLIS?");

        try {
            clientTCP.getOutputStream().write(CommandFormatter.formatForTCP(new String[] {"GLIS?"}));
            clientTCP.getOutputStream().flush();
            DebugLogger.print(DebugType.COM, "CLIENT : " + "GLIS?");
        } catch(IOException e) {
            e.printStackTrace();
        }
        
    }
    
}
