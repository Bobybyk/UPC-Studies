package server.commands;

// import java Classes
import java.io.PrintWriter;

// import our Classes
import server.playingProps.Game;
import server.playingProps.Player;
import server.ServerMain;


public class CommandUpdateRoundManager extends Command {

	public CommandUpdateRoundManager(PrintWriter pw) {
		super("0",pw);
	}

	@Override
	public void execute(Game g, Player p, String[] args) {

		Player[] playerList = g.getPlayers().toArray(new Player[g.getPlayers().size()]);
        int checkForRound = 0;
        if (g.getGameMod() == 2) {
            if (g.getConfirmations() == null) {
                g.setConfirmations(new boolean[playerList.length]);
            }
            if (g.getPlayerRound() == -1) {
                g.setPlayerRound(playerList[0].getPlayerId());
            }
            for (int i = 0 ; i < playerList.length ; i++) {
                if (g.getConfirmations()[i] == false) {
                    g.setConfirmations(i);
                    break;
                }
            }
            for (int i = 0 ; i<g.getConfirmations().length ; i++) {
                if (g.getConfirmations()[i] == false) {
                    break;
                } else {
                    checkForRound++;
                }
            }
            if (checkForRound == playerList.length) {
                Player playerToBroadcast = null;
                for (int i = 0 ; i<playerList.length ; i++) {
                    if (g.getPlayerRound() == playerList[i].getPlayerId()) {
                        for (int j = i ; 1 == 1; j++) {
                            if (j == playerList.length-1) {
                                j = -1;
                            }
                            if (playerList[j+1].isPlayerDead() == false) {
                                g.setPlayerRound(playerList[j+1].getPlayerId());
                                playerToBroadcast = playerList[j+1];
                                System.out.println("round : " + g.getPlayerRound());
                                break;
                            }
                            if (g.areAllPlayersDead()) {
                                broadcastInGame("600 GAME OVER", g.getGameId());
                                break;
                            }
                        }
                        break;
                    }
                }
                broadcastInGame("500 " + playerToBroadcast.getName() + " TURN", g.getGameId());
                g.setConfirmations(new boolean[playerList.length]);
            }
        }
	}
}