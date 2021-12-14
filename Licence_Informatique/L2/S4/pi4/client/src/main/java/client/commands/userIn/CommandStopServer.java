package client.commands.userIn;

import java.io.PrintWriter;

import client.commands.Command;
import client.Player;

public class CommandStopServer extends Command {

	public CommandStopServer(PrintWriter out) {
		super("0",out);
	}

	public void execute(Player p, String[] args) {
		sendMessage("0");
	}
} 