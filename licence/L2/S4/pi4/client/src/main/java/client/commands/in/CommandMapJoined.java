package client.commands.in;

import java.io.PrintWriter;

import client.Player;
import client.GameInfo;
import client.commands.Command;

public class CommandMapJoined extends Command {
	
	public CommandMapJoined(PrintWriter out) {
		super("131",out);
	}

	public void execute(Player p, String[] args) {
		sendMessage("120 GETLIST");
	}
}