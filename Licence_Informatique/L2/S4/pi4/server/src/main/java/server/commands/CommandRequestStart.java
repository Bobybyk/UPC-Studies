package server.commands;

// import java Classes
import java.io.PrintWriter;

// import our Classes
import server.ServerMain;
import server.playingProps.Game;
import server.playingProps.Player;


public class CommandRequestStart extends Command {

	public CommandRequestStart(PrintWriter pw) {
		super("150",pw);
	}

	@Override
	public void execute(Game g, Player p, String[] args) {
		if(g.getOwnerID() == p.getPlayerId()) {
            for(Player p2 : g.getPlayers()) {
                p2.setAnswered(false);
            }
            broadcastInGame("152 START REQUESTED",g.getGameId());

            Thread t = new Thread() {
                public void run() {
                    if(ServerMain.checkForLaunch(g.getGameId())) {
                        ServerMain.launchGame(g.getGameId());
                        broadcastInGame("153 GAME STARTED",g.getGameId());
                        for(Player p : g.getPlayers()) {
                            while(p.getPos()[0] == 0 & p.getPos()[1] == 0) {
                                System.out.print("");
                            }
                            broadcastInGame("510 "+p.getName()+" POS "+p.getPos()[1]+" "+p.getPos()[0],g.getGameId());
                        }
                    } else {
                        String playersNotReady = "";
                        for(Player pl : g.getPlayers()) {
                            if(!pl.getReady())
                                playersNotReady += pl.getName() + " ";
                        }
                        String[] playersNotReadyTab = breakCommand(playersNotReady);
                        broadcastInGame("154 START ABORTED "+playersNotReadyTab.length,g.getGameId());
                        for(int i=0;i<playersNotReadyTab.length;i++) {
                            String req = "154 MESS "+(i+1)+" PLAYER ";

                            for(int j=0; j<5;j++) {
                                req += playersNotReadyTab[i]+" ";
                                i++;
                                if(i == playersNotReadyTab.length)
                                    break;
                            }

                            broadcastInGame(req,g.getGameId());
                        }
                    }
                }
            };
            t.start();
        } else {
            sendMessage("You do not have permission to request start");
        }
	}

}