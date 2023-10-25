package main.java.commands.in.tcp;

import java.io.PrintWriter;
import java.util.LinkedList;

import main.java.client.ClientTCP;
import main.java.commands.CommandTCP;
import main.java.console.Console;
import main.java.console.DebugLogger;
import main.java.console.DebugType;
import main.java.game.GameInfo;
import main.java.game.Player;

// POSIT id x y***

public class CommandRcvTcpPlayerPos extends CommandTCP {

    public CommandRcvTcpPlayerPos(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP clientTCP, LinkedList<Integer> command) {
        DebugLogger.print(DebugType.CONFIRM, "COMMAND : POSIT");

        if (command.size() < 25) {
            DebugLogger.print(DebugType.WARNING, "[CommandRcvTcpPlayerId/WARNING] : les informations données par le serveur sont incomplétes, cette commande sera ignorée");
            return;
        }

        String id = "";
        int count = 0;
        String x = "";
        String y = "";


        // read the id (8 char)
        while(count < 8) {
            id += (char) command.get(6 + count).byteValue();
            count++;
        }

        // vérification que le message était destiné au joueur
        if (!id.equals(GameInfo.playerID)) {
            DebugLogger.print(DebugType.WARNING, "[CommandRcvTcpPlayerPos/WARNING] : le joueur (" + id + ") envoyé par le serveur n'est pas le joueur courant, cette commande sera ignorée");
            return;
        }

        for (int i = 15 ; i < 18 ; i++) {
            x += (char) command.get(i).byteValue();
        }
        for (int i = 19 ; i < 22 ; i++) {
            y += (char) command.get(i).byteValue();
        }

        try {
            GameInfo.players.get(id).setPosX(Integer.parseInt(x));
            GameInfo.players.get(id).setPosY(Integer.parseInt(y));

            DebugLogger.print(DebugType.CONFIRM, "x : " + GameInfo.players.get(id).getPosX() + " y : " + GameInfo.players.get(id).getPosY());
            
        } catch (NumberFormatException e) {
            DebugLogger.print(DebugType.ERROR, "[CommandRcvTcpPlayerPos/ERROR] : les informations de coordonnées du joueur n'ont pas été correctement données par le serveur, le client va être déconnecté");
            Console.useMessage("killclient");
            return;
        } catch (NullPointerException e) {
            GameInfo.players.put(id, new Player(Integer.parseInt(x), Integer.parseInt(y)));
        }

        DebugLogger.print(DebugType.COM, "SERVER : POSIT " + id + " " + x + " " + y);
    }

    @Override
    public void execute(ClientTCP clientTCP, String[] args) {

    }
    
}
