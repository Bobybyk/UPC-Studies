package client.commands.userIn;

import java.io.PrintWriter;

import client.commands.Command;
import client.Player;
import client.control.shell.Console;

public class CommandRequestStartSayNo extends Command {

	public CommandRequestStartSayNo(PrintWriter out) {
		super("152",out);
	}

	public void execute(Player p, String[] args) {
		sendMessage("152 START NO");
		Console.setStartRequested(false);
	}
} 