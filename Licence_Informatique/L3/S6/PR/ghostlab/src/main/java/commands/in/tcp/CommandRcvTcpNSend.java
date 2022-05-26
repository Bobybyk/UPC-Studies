package main.java.commands.in.tcp;

import java.io.PrintWriter;
import java.util.LinkedList;

import main.java.client.ClientTCP;
import main.java.commands.CommandTCP;
import main.java.console.DebugLogger;
import main.java.console.DebugType;
import main.java.game.GameInfo;

// NSEND**

public class CommandRcvTcpNSend extends CommandTCP {

    public CommandRcvTcpNSend(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP clientTCP, LinkedList<Integer> command) {
        DebugLogger.print(DebugType.CONFIRM, "COMMAND : NSEND");

        DebugLogger.print(DebugType.COM, "SERVER : Le message n'a pas été envoyé !");
    }

    @Override
    public void execute(ClientTCP clientTCP, String[] args) {
        // TODO Auto-generated method stub
        
    }
    
    
}
