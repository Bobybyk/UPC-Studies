package server.commands;

// import our Classes
import java.io.PrintWriter;

// import java Classes
import server.playingProps.Game;
import server.playingProps.Player;
import server.ServerMain;


public class CommandSendGameInfo extends Command {

	public CommandSendGameInfo(PrintWriter pw) {
		super("121",pw);
	}

	@Override
	public void execute(Game g, Player p, String[] args) {
		int nbrOfGames = ServerMain.getNumberOfGames();

        sendMessage("121 NUMBER "+nbrOfGames);
        
        int i=0;
        for(Game game : ServerMain.createGames) {
            sendMessage("121 MESS "+ (i++) + " ID " + game.getGameId() + " " + game.getGameMod() + " " + game.getBoard().getSizeX() + " " + game.getBoard().getSizeY() + " " + game.getBoard().getHoleCount() + " " + game.getBoard().getTreasureCount());
        }
	}
}