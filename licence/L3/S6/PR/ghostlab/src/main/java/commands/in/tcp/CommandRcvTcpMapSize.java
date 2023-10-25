package main.java.commands.in.tcp;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.nio.ByteOrder;

import main.java.client.ClientTCP;
import main.java.commands.CommandTCP;
import main.java.console.DebugLogger;
import main.java.console.DebugType;
import main.java.game.GameInfo;

// SIZE! m h w***

public class CommandRcvTcpMapSize extends CommandTCP {

    public CommandRcvTcpMapSize(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP client, LinkedList<Integer> command) {

        DebugLogger.print(DebugType.CONFIRM, "COMMAND : SIZE!");
        
        if (command.size() < 16) {
            DebugLogger.print(DebugType.WARNING, "[CommandRcvTcpMapSize/WARNING] : les informations données par le serveur sont incomplétes, cette commande sera ignorée");
            return;
        }

        // read "m" uint8
        int m = command.get(6);
        int h;
        int w;

        if (ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN)) {
            // read "h" uint16
            h = (command.get(8).byteValue() << 8) | command.get(9).byteValue();
            // read "w" uint16
            w = (command.get(11).byteValue() << 8) | command.get(12).byteValue();     

        } else {
            // read "h" uint16
            h = (command.get(9).byteValue() << 8) | command.get(8).byteValue();
            // read "w" uint16
            w = (command.get(12).byteValue() << 8) | command.get(11).byteValue();
        }

        try {
            GameInfo.games.get(m).setHeight(h);
            GameInfo.games.get(m).setWidth(w);
        } catch (NumberFormatException e) {
            DebugLogger.print(DebugType.WARNING, "[CommandRcvTcpMapSize/WARNING] : les informations données par le serveur pour la taille de la partie sont incohérentes, cette commande sera ignorée");
            return;
        } catch (IndexOutOfBoundsException e) {
           DebugLogger.print(DebugType.ERROR, "[CommandRcvTcpMapSize/ERROR] : la partie n'existe pas, cette commande sera ignorée");
            return;
        }

        DebugLogger.print(DebugType.COM, "SERVER : SIZE! " + m + " " + h + " " + w);

    }

    @Override
    public void execute(ClientTCP clientTCP, String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
