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

// SEND? id mess***

public class CommandAskTcpSendMessage extends CommandTCP {

    public CommandAskTcpSendMessage(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP clientTCP, LinkedList<Integer> command) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void execute(ClientTCP clientTCP, String[] args) {
        DebugLogger.print(DebugType.CONFIRM, "COMMAND : SEND?");

        if (!GameInfo.isInGame) {
            DebugLogger.print(DebugType.WARNING, "[ATTENTION/CommandAskTcpSendMessage] : impossible d'envoyer un message, vous n'êtes pas dans une partie");
            return;
        }

        if(!GameInfo.hasGameStarted) {
            DebugLogger.print(DebugType.WARNING, "[ATTENTION/CommandAskTcpSendMessage] : impossible d'envoyer un message, la partie n'a pas commencé");
            return;
        }

        if (args.length < 3) {
            DebugLogger.print(DebugType.WARNING, "[ATTENTION/CommandAskTcpSendMessage] : il manque des arguments");
            return;
        }

        if (args[1].length() != 8) {
            DebugLogger.print(DebugType.WARNING, "[ATTENTION/CommandAskTcpSendMessage] : l'id du joueur doit être d'exactement 8 caractères");
            return;
        }

        String message = "";
        for (int i = 0 ; i < args.length ; i++) {
            message += args[i];
            message += " ";
        }

        try {
            clientTCP.getOutputStream().write((message+"***").getBytes());
            clientTCP.getOutputStream().flush();
            DebugLogger.print(DebugType.COM, "CLIENT : " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
}
