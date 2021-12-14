package client.commands.in;

import java.io.PrintWriter;

import client.GameInfo;
import client.Player;
import client.commands.Command;

import server.elements.Hole;

public class CommandUpdateHoles extends Command{

	public CommandUpdateHoles(PrintWriter out) {
		super("401",out);
	}

	public void execute(Player p, String[] args) {

		if (args[1].equals("MESS") && args[3].equals("POS")) {
			for(int i=4;i<args.length;i+=2) {
				GameInfo.addHole(new Hole(Integer.parseInt(args[i]),Integer.parseInt(args[i+1])));
			}
		}
	}
}