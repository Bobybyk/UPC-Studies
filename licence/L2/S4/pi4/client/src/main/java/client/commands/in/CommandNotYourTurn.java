package client.commands.in;

import java.io.PrintWriter;

import client.Player;
import client.GameInfo;
import client.commands.Command;

public class CommandNotYourTurn extends Command {
	
	public CommandNotYourTurn(PrintWriter out) {
		super("152",out);
	}

	public void execute(Player p, String[] args) {
		System.out.println("Not your round");
	}
}