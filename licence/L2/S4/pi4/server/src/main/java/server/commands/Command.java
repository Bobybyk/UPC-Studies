package server.commands;

// import java Classes
import java.io.PrintWriter;

// import our Classes
import server.playingProps.Game;
import server.playingProps.Player;
import server.ServerMain;
import server.io.Communication;


public abstract class Command {

	private String code;
	private PrintWriter out;

	public Command(String code, PrintWriter pw) {
		this.code = code;
		out = pw;
	}

	public String toString() {
		return this.code;
	}

	protected void sendMessage(String message) {
		out.println(message);
		out.flush();
	}

	protected void broadcastInGame(String message, int gameID) {       //send a message to all players in a specific game
        for(Communication c : ServerMain.launchedCom) {
            if(c.getPlayer().getGameId() == gameID) {
                c.getOutput().println(message);
                c.getOutput().flush();
            }
        }
    }

   	protected String[] breakCommand(String command) {         //This method breaks the command which arguments are separated by spaces
        String delims = "[ ]+";     
        /*
		 * The delimiter is set. 
         * Here we use "space" as delimiter, brackets indicate the start and end of the group.
         *  "+" indicate that consecutive delimitors should be treated as a single one
         *
         *   It is basically a rational expression :P (Just like what we learned in AAL3 but simpler)
         */
        String[] args = command.split(delims);
        return args;
    }

	public abstract void execute(Game g, Player p, String[] args);
}