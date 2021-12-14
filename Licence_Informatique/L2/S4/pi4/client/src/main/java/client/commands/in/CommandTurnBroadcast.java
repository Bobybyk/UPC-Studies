package client.commands.in;

import java.io.PrintWriter;

import client.Player;
import client.GameInfo;
import client.commands.Command;

public class CommandTurnBroadcast extends Command {
	
	public CommandTurnBroadcast(PrintWriter out) {
		super("500",out);
	}

	public void execute(Player p, String[] args) {
		if (args[1].equals(Player.getName())) {
			GameInfo.setPlayable(true);
			System.out.println("Your turn");
			sendMessage("501 TURN UPDATED");
		} else {
			System.out.println(args[1] + " turn");
		}
	}
}