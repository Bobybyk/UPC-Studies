package client.commands.in;

import java.io.PrintWriter;

import client.Player;
import client.GameInfo;
import client.commands.Command;

public class CommandPlayerDied extends Command {
	
	public CommandPlayerDied(PrintWriter out) {
		super("520",out);
	}

	public void execute(Player p, String[] args) {
		GameInfo.removePlayerCoordinates(args[1]);
		sendMessage("521 " + Player.getName() + " UPDATED");
	}
}