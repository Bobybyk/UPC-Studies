package server.commands;

// import java Classes
import java.io.PrintWriter;

// import our Classes
import server.playingProps.Game;
import server.playingProps.Player;
import server.ServerMain;


public class Command501 extends Command {

	public Command501(PrintWriter pw) {
		super("0",pw);
	}

	@Override
	public void execute(Game g, Player p, String[] args) {
		
		/**
		* client confirm player round is updated 
		*/

		System.out.println(p.getName() + " : player round updated CONFIRMATION");
	}
}
