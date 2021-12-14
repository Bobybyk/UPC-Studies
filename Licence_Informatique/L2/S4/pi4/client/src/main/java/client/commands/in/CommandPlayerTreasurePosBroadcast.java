package client.commands.in;

import java.io.PrintWriter;

import client.Player;
import client.GameInfo;
import client.commands.Command;

import server.elements.Treasure;

public class CommandPlayerTreasurePosBroadcast extends Command {
	
	public CommandPlayerTreasurePosBroadcast(PrintWriter out) {
		super("511",out);
	}

	public void execute(Player p, String[] args) {
		for(Treasure tr : GameInfo.getTreasures()) {
			
			if(tr.getX() == Integer.parseInt(args[4]) && tr.getY() == Integer.parseInt(args[3]) && tr.getTreasureValue() == Integer.parseInt(args[6])) {
				GameInfo.removeTreasure(tr);
				break;
			}
		}

		GameInfo.setPlayerCoordinates(args[1],Integer.parseInt(args[3]),Integer.parseInt(args[4]));
		sendMessage("512 " + args[1] + " UPDATED");
		Player.printGameBoard();
	}
}