package main.java.commands.in.tcp;

import java.io.PrintWriter;
import java.util.LinkedList;

import main.java.client.ClientTCP;
import main.java.commands.CommandTCP;
import main.java.console.DebugLogger;
import main.java.console.DebugType;

// REGNO***

public class CommandRcvTcpJoinNO extends CommandTCP{

    public CommandRcvTcpJoinNO(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP client, LinkedList<Integer> command) {
        DebugLogger.print(DebugType.CONFIRM, "COMMAND : REGNO");
        DebugLogger.print(DebugType.COM, "SERVER : REGNO");
    }

    @Override
    public void execute(ClientTCP clientTCP, String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
