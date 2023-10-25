package client.commands.userIn;

import java.io.PrintWriter;

import client.commands.Command;
import client.Player;

public class CommandRequestStart extends Command {

	public CommandRequestStart(PrintWriter out) {
		super("150",out);
	}

	public void execute(Player p, String[] args) {
		sendMessage("150");
	}
} 