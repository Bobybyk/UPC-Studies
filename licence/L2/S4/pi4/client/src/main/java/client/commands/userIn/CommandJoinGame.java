package client.commands.userIn;

import java.io.PrintWriter;

import client.commands.Command;
import client.Player;

public class CommandJoinGame extends Command {

	public CommandJoinGame(PrintWriter out) {
		super("130",out);
	}

	public void execute(Player p, String[] args) {
		if(args.length != 2) {
			System.out.println("Command syntax error");
		} else 
			sendMessage("130 JOIN "+args[1]);
	}
} 