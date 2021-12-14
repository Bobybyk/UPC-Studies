package server.commands;

// import java Classes
import java.io.PrintWriter;

// import our Classes
import server.playingProps.Player;
import server.playingProps.Game;


public class CommandRequestStartResponse extends Command {

	public CommandRequestStartResponse(PrintWriter pw) {
		super("152",pw);
	}

	@Override
	public void execute(Game g, Player p, String[] args) {

		/**
        * check the response to resquest start (case 153)
		*/

		if(args[2].equals("YES")) {
            p.setReady(true);
		} else {
            p.setReady(false);
        }
        	p.setAnswered(true);
	}
}