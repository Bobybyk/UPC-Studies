package client.commands.in;

import java.io.PrintWriter;

import client.GameInfo;
import client.Player;
import client.commands.Command;

import server.elements.Treasure;

public class CommandUpdateTreasures extends Command{
	
	public CommandUpdateTreasures(PrintWriter out) {
		super("411",out);
	}

	public void execute(Player p, String[] args) {

		if (args[1].equals("MESS") && args[3].equals("POS")) {
			for(int i=4;i<args.length;i+=3) {
				GameInfo.addTreasure(new Treasure(Integer.parseInt(args[i]),Integer.parseInt(args[i+1]),Integer.parseInt(args[i+2])));
			}
		}
		
	}
}