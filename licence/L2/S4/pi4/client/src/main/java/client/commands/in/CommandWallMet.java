package client.commands.in;

import java.io.PrintWriter;

import client.Player;
import client.GameInfo;
import client.commands.Command;

public class CommandWallMet extends Command {
	
	public CommandWallMet(PrintWriter out) {
		super("202",out);
	}

	public void execute(Player p, String[] args) {
		System.out.println("Impossible movement, wall met");
	}
}