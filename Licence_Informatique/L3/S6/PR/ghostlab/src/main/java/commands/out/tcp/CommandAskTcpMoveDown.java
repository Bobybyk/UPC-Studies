package main.java.commands.out.tcp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import main.java.client.ClientTCP;
import main.java.commands.CommandTCP;
import main.java.console.DebugLogger;
import main.java.console.DebugType;
import main.java.game.GameInfo;

// DOMOV d***

public class CommandAskTcpMoveDown extends CommandTCP {

    public CommandAskTcpMoveDown(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP clientTCP, LinkedList<Integer> command) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void execute(ClientTCP clientTCP, String[] args) {
        DebugLogger.print(DebugType.CONFIRM, "COMMAND : DOMOV");

        if (!GameInfo.hasGameStarted) {
            DebugLogger.print(DebugType.ERROR, "[CommandAskTcpDropGame/WARNING] : le jeu n'a pas encore commencé, cette commande sera ignorée");
            return;
        }
        
        if (args.length < 2) {
            DebugLogger.print(DebugType.WARNING, "[CommandAskTcpMoveDown/WARNING] : nombre d'arguments minimum non attend, (RAPPEL : DOMOV d), cette commande sera ignorée");
            return;
        }

        try {
           
            int d = Integer.parseInt(args[1]);
            String dStr;
           
            if (d > 999) {
                DebugLogger.print(DebugType.WARNING, "[CommandAskTcpMoveDown/WARNING] : d doit être inférieur ou égal à 999, cette commande sera ignorée");
                return;
            }
            if (d < 0) {
                DebugLogger.print(DebugType.WARNING, "[CommandAskTcpMoveDown/WARNING] : d doit être supérieur ou égal à 0, cette commande sera ignorée");
                return;
            }

            if (d < 10) {
                dStr = "00" + d;
            }
            else if (d < 100) {
                dStr = "0" + d;
            }
            else {
                dStr = "" + d;
            }

            GameInfo.players.get(GameInfo.playerID).setShiftingAsked(d);
            GameInfo.lastMoveDirection = 2;

            clientTCP.getOutputStream().write((args[0] +" "+dStr+"***").getBytes());
            clientTCP.getOutputStream().flush();
            DebugLogger.print(DebugType.COM, "CLIENT : " + args[0] + " " + dStr);   

        } catch (NumberFormatException e) {
            DebugLogger.print(DebugType.WARNING, "[CommandAskTcpMoveDown/WARNING] : d doit être un nombre, cette commande sera ignorée");
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
}
