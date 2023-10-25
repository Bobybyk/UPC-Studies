package client.commands.in;

import java.io.PrintWriter;

import client.Player;
import client.GameInfo;
import client.commands.Command;

public class CommandEveryoneDead extends Command {
	
	public CommandEveryoneDead(PrintWriter out) {
		super("600",out);
	}

	public void execute(Player p, String[] args) {
		System.out.println("Everyone is dead : GAME OVER");
		System.exit(0);
	}
}