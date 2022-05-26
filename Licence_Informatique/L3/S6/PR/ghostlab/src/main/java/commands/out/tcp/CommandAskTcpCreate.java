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


// NEWPL id port***

public class CommandAskTcpCreate extends CommandTCP {

    public CommandAskTcpCreate(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP clientTCP, String[] args) {
        DebugLogger.print(DebugType.CONFIRM, "COMMAND : create game command (NEWPL)");

        if (GameInfo.isInGame) {
            DebugLogger.print(DebugType.WARNING, "[ATTENTION/CommandAskTcpCreate] : vous êtes déjà inscrit à une partie, vous ne pouvez pas en rejoindre une autre... Veuillez préalablement vous désinscrire : UNREG");
            return;
        }

        if (GameInfo.playerID.length() != 8) {
            DebugLogger.print(DebugType.WARNING, "[ATTENTION/CommandAskJoin] La taille de votre id doit être d'exactement 8 caractères");
            return;
        }

        String[] command = {args[0], GameInfo.playerID, Integer.toString(GameInfo.portUDP)};

        try {
            clientTCP.getOutputStream().write((args[0]+" "+GameInfo.playerID+" "+Integer.toString(GameInfo.portUDP)+"***").getBytes());
            clientTCP.getOutputStream().flush();
            DebugLogger.print(DebugType.COM, "CLIENT : " + args[0] + " " + GameInfo.playerID + " " + GameInfo.portUDP);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void execute(ClientTCP clientTCP, LinkedList<Integer> command) {
        // TODO Auto-generated method stub
        
    }
    
}
