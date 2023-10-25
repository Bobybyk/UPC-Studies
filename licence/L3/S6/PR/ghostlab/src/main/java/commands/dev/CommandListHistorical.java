package main.java.commands.dev;

import java.util.HashMap;

import main.java.commands.CommandDev;
import main.java.console.DebugLogger;
import main.java.console.DebugType;
import main.java.game.GameInfo;
import main.java.game.Player;

public class CommandListHistorical extends CommandDev {

    @Override
    public void execute(String[] args) {
        DebugLogger.print(DebugType.CONFIRM, "COMMAND : historical");
        
        DebugLogger.print(DebugType.HELP, "###########################################################################");
        DebugLogger.print(DebugType.HELP, "#                                                                         #");
        DebugLogger.print(DebugType.HELP, "#                        Historique des parties jouées                    #");
        DebugLogger.print(DebugType.HELP, "#                                                                         #");
        DebugLogger.print(DebugType.HELP, "###########################################################################");
        DebugLogger.print(DebugType.HELP, "");

        if (GameInfo.gameHistory.isEmpty()) {
            DebugLogger.print(DebugType.HELP, "Aucune partie n'a été jouée");
            return;
        }

        for (int i = 0; i < GameInfo.gameHistory.size(); i++) {
            DebugLogger.print(DebugType.HELP, "Partie n°" + (i+1) + " : ");

            if (GameInfo.gameHistory.get(i).getPlayerID() == null) {
                DebugLogger.print(DebugType.HELP, "  - Joueur : " + "[unknown]");
            } else {
                DebugLogger.print(DebugType.HELP, "  - Joueur : " + GameInfo.gameHistory.get(i).getPlayerID());
            }

            if (GameInfo.gameHistory.get(i).getWinner().equals("") || GameInfo.gameHistory.get(i).getWinnerScore() == 0) {
                DebugLogger.print(DebugType.HELP, "  - Gagnant : " + "[unknown]");
            } else {
                DebugLogger.print(DebugType.HELP, "  - Gagnant : " + GameInfo.gameHistory.get(i).getWinner() + ", " + GameInfo.gameHistory.get(i).getWinnerScore() + " points");
            }

            DebugLogger.print(DebugType.HELP, "  - Scores : ");

            for (HashMap.Entry<String, Integer> scores : GameInfo.gameHistory.get(i).getPlayers().entrySet()) {
                DebugLogger.print(DebugType.HELP, "      * " + scores.getKey() + " : " + scores.getValue());
            }
            
            DebugLogger.print(DebugType.HELP, "  - Messages : ");

            if (GameInfo.gameHistory.get(i).getMessagesHistory().isEmpty()) {
                DebugLogger.print(DebugType.HELP, "      * [empty]");
            } else {
                for (int j = 0 ; j < GameInfo.gameHistory.get(i).getMessagesHistory().size(); j++) {
                    DebugLogger.print(DebugType.HELP, "      * Message n°" + j + " : " + GameInfo.gameHistory.get(i).getMessagesHistory().get(j));
                }
            } 

            DebugLogger.print(DebugType.HELP, "");
        }

        DebugLogger.print(DebugType.HELP, "");
        DebugLogger.print(DebugType.HELP, "---------------------------------------------------------------------------");
        DebugLogger.print(DebugType.HELP, "");
    }
    
}
