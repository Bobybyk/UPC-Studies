package client.commands.in;

import java.io.PrintWriter;

import client.Player;
import client.GameInfo;
import client.commands.Command;

public class CommandUpdateMapList extends Command {
	
	public CommandUpdateMapList(PrintWriter out) {
		super("121",out);
	}

	public void execute(Player p, String[] args) {
		updateGameList(args);

		int mapID = p.getGameId();
		int[][] games = GameInfo.getJoinableGames();
		for(int i=0;i<games.length;i++) {
			if(games[i][0] == mapID) {
				GameInfo.setMap(games[i][2],games[i][3]);
				break;
			}
		}
	}

	private void updateGameList(String args[]) {
		if(args[1].equals("NUMBER")) {
			GameInfo.setGameNumber(Integer.parseInt(args[2]));
			GameInfo.setNumberOfGames(Integer.parseInt(args[2]));
		} else if(args[1].equals("MESS") && args[3].equals("ID")) {
			int[] tab = new int[6];
			for(int i=0;i<6;i++)
				tab[i] = Integer.parseInt(args[4+i]);
			int games[][] = GameInfo.getJoinableGames();
			for(int i=0;i<games.length;i++) {
				boolean insert = true;
				for(int j=0;j<games[i].length;j++) {
					if(games[i][j] != 0) {
						insert = false;
						break;
					}
				}
				if(insert) {
					GameInfo.setGamePos(i,tab);
					break;
				}
			}
		}
	}
}