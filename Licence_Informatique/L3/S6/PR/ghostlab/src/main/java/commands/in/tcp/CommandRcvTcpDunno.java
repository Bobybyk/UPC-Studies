package main.java.commands.in.tcp;

import java.io.PrintWriter;
import java.util.LinkedList;

import main.java.client.ClientTCP;
import main.java.commands.CommandTCP;
import main.java.console.DebugLogger;
import main.java.console.DebugType;

// DUNNO***

public class CommandRcvTcpDunno extends CommandTCP {

    public CommandRcvTcpDunno(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP client, LinkedList<Integer> command) {
        DebugLogger.print(DebugType.CONFIRM, "COMMAND : DUNNO");
        DebugLogger.print(DebugType.COM, "SERVER : DUNNO");

    }

    @Override
    public void execute(ClientTCP clientTCP, String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
