package client.commands.in;

import java.io.PrintWriter;

import client.GameInfo;
import client.Player;
import client.commands.Command;
import server.elements.*;

public class CommandUpdateWalls extends Command{

	public CommandUpdateWalls(PrintWriter out) {
		super("421",out);
	}

	public void execute(Player p, String[] args) {

		if (args[1].equals("MESS") && args[3].equals("POS")) {
			for(int i=4;i<args.length;i+=2) {
				GameInfo.addWall(new Wall(Integer.parseInt(args[i]),Integer.parseInt(args[i+1])));
			}
		} 
	}
}