package client.commands.userIn;

import java.io.PrintWriter;

import client.commands.Command;
import client.Player;
import client.control.shell.Console;

public class CommandRequestStartSayYes extends Command {

	public CommandRequestStartSayYes(PrintWriter out) {
		super("152",out);
	}

	public void execute(Player p, String[] args) {
		sendMessage("152 START YES");
		Console.setStartRequested(false);
	}
} 