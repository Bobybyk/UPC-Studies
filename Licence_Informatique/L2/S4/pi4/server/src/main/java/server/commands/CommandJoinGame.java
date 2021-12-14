package server.commands;

// import java Classes
import java.io.PrintWriter;

// import our Classes
import server.ServerMain;
import server.playingProps.Player;
import server.playingProps.Game;


public class CommandJoinGame extends Command {

	public CommandJoinGame(PrintWriter pw) {
		super("130",pw);
	}

	@Override
	public void execute(Game g, Player p, String[] args) {

		/**
         * to join a game with the id
         * if the given id exist : 
         *  join the associate game
         *  send confirmation message to the player (131 command) with the game id
         *  broadcast to every players in game 140 command with the new player name
         * else we send to the sender that the specified game doesn't exist
         */

        if(ServerMain.joinGame(args)) {                          
            sendMessage("131 MAP "+p.getGameId()+" JOINED");
            broadcastInGame("140 "+p.getName()+" JOINED",p.getGameId());                
        } else {
            sendMessage("No such game found");
        }
	}
}