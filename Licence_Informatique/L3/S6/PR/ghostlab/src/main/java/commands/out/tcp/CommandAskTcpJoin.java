package main.java.commands.out.tcp;

import java.io.PrintWriter;
import java.util.LinkedList;

import main.java.client.ClientTCP;
import main.java.commands.CommandTCP;
import main.java.commands.CommandFormatter;
import main.java.console.DebugLogger;
import main.java.console.DebugType;
import main.java.game.GameInfo;

import java.io.IOException;


// REGIS id port m***

public class CommandAskTcpJoin extends CommandTCP {

    public CommandAskTcpJoin(PrintWriter pw) {
        super(pw);
    }

    @Override
    public void execute(ClientTCP clientTCP, String[] args) {

        DebugLogger.print(DebugType.CONFIRM, "COMMAND : ask join game command (REGIS)");
        
        if (GameInfo.isInGame) {
            DebugLogger.print(DebugType.WARNING, "[ATTENTION/CommandAskTcpJoin] : vous êtes déjà inscrit à une partie, vous ne pouvez pas en rejoindre une autre... Veuillez préalablement vous désinscrire : UNREG");
            return;
        }

        if (args.length < 2) {
            DebugLogger.print(DebugType.WARNING, "[ATTENTION/CommandAskJoin] Tout les paramètres de la commande ne sont pas renseignés. Rappel : REGIS numPartie");
            return;
        }

        if (GameInfo.playerID.length() != 8) {
            DebugLogger.print(DebugType.WARNING, "[ATTENTION/CommandAskJoin] La taille de votre id doit être d'exactement 8 caractères");
            return;
        }

        try {
            int i = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            DebugLogger.print(DebugType.WARNING, "[ATTENTION/CommandAskJoin] Le type du numéro de la partie n'est pas conforme");
            return;
        }

        try {
            GameInfo.games.get(Integer.parseInt(args[1]));
        } catch (IndexOutOfBoundsException e) {
            DebugLogger.print(DebugType.WARNING, "[ATTENTION/CommandAskJoin] La partie donnée n'existe pas");
            return;
        }

        String[] command = {args[0], GameInfo.playerID, Integer.toString(GameInfo.portUDP), args[1]};

        try {
            clientTCP.getOutputStream().write(CommandFormatter.formatForTCP(command));
            clientTCP.getOutputStream().flush();
            DebugLogger.print(DebugType.COM, "CLIENT : " + args[0] + " " + GameInfo.playerID + " " + GameInfo.portUDP + " " + args[1]);
        } catch(IOException e) { 
            e.printStackTrace();
        }

    }

    @Override
    public void execute(ClientTCP clientTCP, LinkedList<Integer> command) {
        // TODO Auto-generated method stub
        
    }
    
}
