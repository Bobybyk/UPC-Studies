package main.java.commands.in.tcp;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.nio.ByteOrder;

import main.java.client.ClientTCP;
import main.java.commands.CommandTCP;
import main.java.console.DebugLogger;
import main.java.console.DebugType;
import main.java.game.GameInfo;
import main.java.game.Player;

// WELCO m h w f ip port***

public class CommandRcvMultTcpWelco extends CommandTCP {

    public CommandRcvMultTcpWelco(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP clientTCP, LinkedList<Integer> command) {
        
        DebugLogger.print(DebugType.CONFIRM, "COMMAND : WELCO");

        if (command.size() < 32) {
            DebugLogger.print(DebugType.ERROR, "[CommandRcvTcpWelco/ERROR] : les informations données par le serveur sont incomplétes, cette commande sera ignorée");
            return;
        }

        try {
            GameInfo.registredGameId = command.get(6).byteValue();
        } catch(NumberFormatException e) {
            DebugLogger.print(DebugType.ERROR, "[CommandRcvTcpWelco/ERROR] : les informations données par le serveur pour m ne sont pas conformes, ces informations seront ignorées");
        }

        try {

            if (ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN)) {
                // read "h" uint16
                GameInfo.gameHeight = (command.get(8).byteValue() << 8) | command.get(9).byteValue();
                // read "w" uint16
                GameInfo.gameWidth = (command.get(11).byteValue() << 8) | command.get(12).byteValue();     


            } else {
                // read "h" uint16
                GameInfo.gameHeight = (command.get(9).byteValue() << 8) |  command.get(8).byteValue();
                // read "w" uint16
                GameInfo.gameWidth = (command.get(12).byteValue() << 8) |  command.get(11).byteValue();
            }
        } catch (NumberFormatException e) {
            DebugLogger.print(DebugType.ERROR, "[CommandRcvTcpWelco/ERROR] : les informations données par le serveur pour h et/ou w ne sont pas conformes, ces informations seront ignorées");
        }

        try {
            GameInfo.nbrGhosts = command.get(14).byteValue();
        } catch (NumberFormatException e) {
            DebugLogger.print(DebugType.ERROR, "[CommandRcvTcpWelco/ERROR] : les informations données par le serveur pour f nombre de fantômes ne sont pas conformes, ces informations seront ignorées");
        }

        String ip = "";

        try {
            for (int i = 16 ; i < 31 ; i++) {
                if ((char) command.get(i).byteValue() == '#') {
                    break;
                }
                ip += (char) command.get(i).byteValue();
            }
            GameInfo.ipMulticast = ip;
        } catch (NumberFormatException e) {
            DebugLogger.print(DebugType.ERROR, "[CommandRcvTcpWelco/ERROR] : les informations données par le serveur pour l'ip de multicast ne sont pas conformes, ces informations seront ignorées");
        }

        String port = "";

        try {
            for (int i = 32 ; i < 36 ; i++) {
                port += (char) command.get(i).byteValue();
            }
            GameInfo.portMulticast = Integer.parseInt(port);
        } catch (NumberFormatException e) {
            DebugLogger.print(DebugType.ERROR, "[CommandRcvTcpWelco/ERROR] : les informations données par le serveur pour le port de mutlicast ne sont pas conformes, ces informations seront ignorées");
        }

        GameInfo.hasGameStarted = true;

        //GameInfo.players.put(GameInfo.playerID, new Player()); 

        DebugLogger.print(DebugType.CONFIRM, "SERVER : WELCO " + GameInfo.registredGameId + " " + GameInfo.gameHeight + " " + GameInfo.gameWidth + " " + GameInfo.nbrGhosts + " " + GameInfo.ipMulticast + " " + GameInfo.portMulticast);

    }

    @Override
    public void execute(ClientTCP clientTCP, String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
