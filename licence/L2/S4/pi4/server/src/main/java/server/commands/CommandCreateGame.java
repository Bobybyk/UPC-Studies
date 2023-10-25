package server.commands;

// import java Classes
import java.io.PrintWriter;

// import our Classes
import server.playingProps.Game;
import server.playingProps.Player;
import server.ServerMain;


public class CommandCreateGame extends Command {

	public CommandCreateGame(PrintWriter pw) {
		super("110",pw);
	}

	@Override
	public void execute(Game g, Player p, String[] args) {
		int id = ServerMain.createGame(Integer.parseInt(args[2]),Integer.parseInt(args[4]),Integer.parseInt(args[5]),Integer.parseInt(args[7]),Integer.parseInt(args[9]),p.getPlayerId());
        sendMessage("111 MAP CREATED " + id);
        String s = ""+id;
        String infos[] = {"130","JOIN",s,p.getName()};
        if(ServerMain.joinGame(infos)) {
            sendMessage("131 MAP "+p.getGameId()+" JOINED");
            broadcastInGame("The player "+p.getName()+" joined the game",p.getGameId());
        }
	}

}