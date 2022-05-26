package main.java.commands.in.udp_multicast;

import main.java.commands.CommandUDP;
import main.java.console.DebugLogger;
import main.java.console.DebugType;
import main.java.game.GameInfo;
import main.java.game.Player;

// SCORE id p x y+++

public class CommandRcvMultUdpScore extends CommandUDP {

    @Override
    public void execute(String[] args) {

        DebugLogger.print(DebugType.CONFIRM, "COMMAND : SCORE");

        if(args.length < 5) {
            DebugLogger.print(DebugType.WARNING, "[CommandRcvUdpScore/WARNING] : les informations données par le serveur sont incomplétes, cette commande sera ignorée");
            return;
        }

        if (!args[1].equals(GameInfo.playerID)) {
            try {
                Player p = GameInfo.players.get(args[1]);
                p.setScore(Integer.parseInt(args[2]));
                p.setLastPosX(Integer.parseInt(args[3]));
                p.setLastPosY(Integer.parseInt(args[4]));
            } catch (NullPointerException e) {
                GameInfo.players.put(args[1], new Player(Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4])));
            }

        }


        DebugLogger.print(DebugType.COM, "SERVER : " + args[0] + " " + args[1] + " " + args[2] + " " + args[3] + " " + args[4]);

    }

}
