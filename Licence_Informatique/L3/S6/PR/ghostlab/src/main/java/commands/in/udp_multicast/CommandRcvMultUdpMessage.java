package main.java.commands.in.udp_multicast;

import main.java.commands.CommandUDP;
import main.java.console.DebugLogger;
import main.java.console.DebugType;
import main.java.game.GameInfo;

// MESSA id mess+++

public class CommandRcvMultUdpMessage extends CommandUDP {

    @Override
    public void execute(String[] args) {

        DebugLogger.print(DebugType.CONFIRM, "COMMAND : MESSA");

        //System.out.println(args[1] + " : " + args[2]);

        if (args.length < 3) {
            DebugLogger.print(DebugType.WARNING, "[CommandRcvUdpMessage/WARNING] : les informations données par le serveur sont incomplétes, cette commande sera ignorée");
            return;
        }

        String message = "";

        for(int i=2;i<args.length;i++) {
            message += args[i];
            message += " ";
        }

        DebugLogger.print(DebugType.MESSAGE, "SERVER_" + args[1] + ": "+message);

        GameInfo.messagesHistory.add(message);

    }
    
}
