package main.java.commands.in.udp_multicast;
import main.java.commands.CommandUDP;
import main.java.console.Console;
import main.java.console.DebugLogger;
import main.java.console.DebugType;
import main.java.game.GameHistory;
import main.java.game.GameInfo;
import main.java.game.Player;

// ENDGA id p+++

public class CommandRcvMultUdpEndGame extends CommandUDP {

    @Override
    public void execute(String[] args) {

        DebugLogger.print(DebugType.CONFIRM, "COMMAND : ENDGAME");

        if (args.length < 3) {
            DebugLogger.print(DebugType.WARNING, "[CommandRcvUdpEndGame/WARNING] : les informations données par le serveur sont incomplétes, cette commande sera ignorée");
            return;
        }

        DebugLogger.print(DebugType.COM, "SERVER: "+args[0]+" "+args[1]+" "+args[2]);

        DebugLogger.print(DebugType.MESSAGE, "\u001B[32mPartie terminée, " + args[1] + " gagne avec " + Integer.parseInt(args[2]) + " points !\u001B[37m");

        Player current = GameInfo.players.get(args[1]);

        GameInfo.players.put(args[1], new Player(Integer.parseInt(args[2]), current.getPosX(), current.getPosY()));

        GameInfo.gameHistory.add(new GameHistory(GameInfo.playerID, GameInfo.players, GameInfo.messagesHistory));

        Console.useMessage("IQUIT");

        // retour au lobby

    }

    
}
