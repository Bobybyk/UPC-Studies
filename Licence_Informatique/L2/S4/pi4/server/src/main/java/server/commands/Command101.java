package server.commands;

// import java Classes
import java.io.PrintWriter;

// import our Classes
import server.playingProps.Game;
import server.playingProps.Player;
import server.ServerMain;


public class Command101 extends Command {

	public Command101(PrintWriter pw) {
		super("101",pw);
	}

	@Override
	public void execute(Game g, Player p, String[] args) {
		sendMessage("101 WELCOME "+p.getName());
	}
}
