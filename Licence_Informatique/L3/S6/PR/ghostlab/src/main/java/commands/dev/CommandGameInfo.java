package main.java.commands.dev;


import main.java.commands.CommandDev;
import main.java.console.DebugLogger;
import main.java.console.DebugType;
import main.java.game.GameInfo;

public class CommandGameInfo extends CommandDev {

    @Override
    public void execute(String[] args) {
        DebugLogger.print(DebugType.CONFIRM, "COMMAND : gameinfo");

        DebugLogger.print(DebugType.HELP, "Nombre de parties trouvées et disponibles : " + GameInfo.nbrGames);
        
        if (GameInfo.games.isEmpty()) {
            DebugLogger.print(DebugType.HELP, "Pas de parties trouvées");
            return;
        }

        for (int i = 0 ; i < GameInfo.games.size() ; i++) {
            DebugLogger.print(DebugType.HELP, "Game nbr : " + i + ", nombre de joueurs : " + GameInfo.games.get(i).getNbrPlayers());
        }
        
    }
    
}
