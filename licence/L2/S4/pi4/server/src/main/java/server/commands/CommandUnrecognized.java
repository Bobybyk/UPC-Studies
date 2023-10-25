package server.commands;

// import java Classes
import java.io.PrintWriter;

// import our Classes
import server.playingProps.Game;
import server.playingProps.Player;
import server.ServerMain;


public class CommandUnrecognized extends Command {

	public CommandUnrecognized(PrintWriter pw) {
		super("0",pw);
	}

	@Override
	public void execute(Game g, Player p, String[] args) {
		sendMessage("999 COMMAND ERROR");
	}
}