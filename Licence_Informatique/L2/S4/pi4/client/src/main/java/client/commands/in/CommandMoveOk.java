package client.commands.in;

import java.io.PrintWriter;

import client.Player;
import client.GameInfo;
import client.commands.Command;

import client.control.shell.Console;

public class CommandMoveOk extends Command {
	
	public CommandMoveOk(PrintWriter out) {
		super("201",out);
	}

	public void execute(Player p, String[] args) {
		int last = Console.getLastMove();
		int[] pos = GameInfo.getPlayerCoordinates(p.getName());
		int j=0;
		if (last == 1) {
			System.out.println("MOVE OK : UP");
			pos[1]--;
		}
		else if (last == 2) {
			System.out.println("MOVE OK : DOWN");
			pos[1]++;
		}
		else if (last == 3) {
			System.out.println("MOVE OK : RIGHT");
			pos[0]++;
		}
		else if (last == 4) {
			System.out.println("MOVE OK : LEFT");
			pos[0]--;
		}
		else {
			System.out.println("Error : no move engaged");
		}
		GameInfo.setPlayerCoordinates(p.getName(),pos[0],pos[1]);
	}
}