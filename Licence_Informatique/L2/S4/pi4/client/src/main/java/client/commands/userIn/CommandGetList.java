package client.commands.userIn;

import java.io.PrintWriter;

import client.commands.Command;
import client.Player;

public class CommandGetList extends Command {

	public CommandGetList(PrintWriter out) {
		super("120",out);
	}

	public void execute(Player p, String[] args) {
		sendMessage("120 GETLIST");
	}
} 