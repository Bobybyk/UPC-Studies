package main.java.commands.in.udp;

import main.java.commands.CommandUDP;
import main.java.console.DebugLogger;
import main.java.console.DebugType;
import main.java.game.GameInfo;

// MESSP id2 mess+++

public class CommandRcvUdpPrivateMess extends CommandUDP {

    @Override
    public void execute(String[] args) {
        
        DebugLogger.print(DebugType.CONFIRM, "COMMAND : MESSP");

        if (args.length < 3) {
            DebugLogger.print(DebugType.WARNING, "[CommandRcvUdpPrivateMess/WARNING] : les informations données par le serveur sont incomplétes, cette commande sera ignorée");
            return;
        }

        String message = "";

        for(int i=2;i<args.length;i++) {
            message += args[i];
            message += " ";
        }

        DebugLogger.print(DebugType.MESSAGE, "SERVER [PRIVE] - " + args[1] + ": "+message);

        GameInfo.messagesHistory.add(message);
        
    }
    
}
