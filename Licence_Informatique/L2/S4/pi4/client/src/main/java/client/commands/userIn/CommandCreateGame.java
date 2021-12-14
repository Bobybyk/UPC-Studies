package client.commands.userIn;

import java.io.PrintWriter;

import client.commands.Command;
import client.Player;

public class CommandCreateGame extends Command {

	public CommandCreateGame(PrintWriter out) {
		super("110",out);
	}

	public void execute(Player p, String[] args) {
		if(args.length != 6) {
			System.out.println("Command syntax error");
		} else {
			createGame(Integer.parseInt(args[1]),Integer.parseInt(args[2]),Integer.parseInt(args[3]),Integer.parseInt(args[4]),Integer.parseInt(args[5]));		
		}
	}

	private void createGame(int gamemode, int sizeX, int sizeY, int holeNumber, int treasureNumber) {
		sendMessage("110 CREATE "+gamemode+" SIZE "+sizeX+" "+sizeY+" HOLE "+holeNumber+" TRES "+treasureNumber);
	}
} 