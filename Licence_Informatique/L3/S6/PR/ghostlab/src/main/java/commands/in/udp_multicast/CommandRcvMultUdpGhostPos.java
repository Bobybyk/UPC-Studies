package main.java.commands.in.udp_multicast;

import main.java.commands.CommandUDP;
import main.java.console.DebugLogger;
import main.java.console.DebugType;
import main.java.game.GameInfo;
import main.java.game.Ghost;

// GHOST x y+++

public class CommandRcvMultUdpGhostPos extends CommandUDP {

    @Override
    public void execute(String[] args) {
        
        DebugLogger.print(DebugType.CONFIRM, "COMMAND : GHOST");

        if (args.length < 3) {
            DebugLogger.print(DebugType.WARNING, "[CommandRcvUdpGhostPos/WARNING] : les informations données par le serveur sont incomplétes, cette commande sera ignorée");
            return;
        }

        try {
            int GhostPosX = Integer.parseInt(args[1]);
            int GhostPosY = Integer.parseInt(args[2]);

            GameInfo.ghosts.add(new Ghost(GhostPosX, GhostPosY,  System.currentTimeMillis()/1000));
        } catch (NumberFormatException e) {
            DebugLogger.print(DebugType.ERROR, "[CommandRcvUdpGhostPos/ERROR] : les informations de coordonnées données par le serveur ne sont pas conformes, cette commande sera ignorée");
        }

        DebugLogger.print(DebugType.COM, "SERVER: "+args[0]+" "+args[1]+" "+args[2]);

    }
    
}
