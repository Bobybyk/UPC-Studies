package client.commands.in;

import java.io.PrintWriter;

import client.Player;
import client.GameInfo;
import client.commands.Command;

public class CommandPlayerPosBroadcast extends Command {
	
	public CommandPlayerPosBroadcast(PrintWriter out) {
		super("510",out);
	}

	public void execute(Player p, String[] args) {
		GameInfo.setPlayerCoordinates(args[1],Integer.parseInt(args[3]),Integer.parseInt(args[4]));
		sendMessage("512 " + args[1] + " UPDATED");
		Player.printGameBoard();
	}
}