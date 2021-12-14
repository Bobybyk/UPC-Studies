package server.commands;

// import java Classes
import java.io.PrintWriter;

// import our Classes
import server.ServerMain;
import server.elements.*;
import server.playingProps.Player;
import server.playingProps.Game;


public class CommandPlayerMovement extends Command {
	
	public CommandPlayerMovement(PrintWriter pw) {
		super("20*",pw);
	}

	public void execute(Game g, Player p, String[] args) {

		/**
         * try to move the current player
         * check if there is something on the new position
         * if yes :
         *  abort the movement if it's a wall
         *  kill the player if it's a hole
         *  reward the player if it's a treasure and move it on the new coordinates
         * if not :
         *  move the player on the new coordinates
         * send the new game state to the client
         */

		int[] pos = p.getPos();
        if (p.getPlayerId() != g.getPlayerRound() && g.getGameMod() == 2) {
            sendMessage("902 NOT YOUR TURN");
        } else {
            switch(args[1]) {
                case "GOUP":
                    pos[0]--;
                    break;
                case "GODOWN":
                    pos[0]++;
                    break;
                case "GOLEFT":
                    pos[1]--;
                    break;
                case "GORIGHT":
                    pos[1]++;
                    break;
                default:
                    break;
            }
            String ret = p.setPos(g.getBoard(),pos);
            pos = p.getPos();
            if(ret.equals("ok")) {
    
                sendMessage("201 MOVE OK");
                broadcastInGame("510 "+p.getName()+" POS "+pos[1]+" "+pos[0],g.getGameId());
    
            } else if(ret.equals("Wall")) {
    
                sendMessage("202 MOVE BLOCKED");
                broadcastInGame("510 "+p.getName()+" POS "+pos[1]+" "+pos[0],g.getGameId());
    
            } else if(ret.equals("Treasure")) {
    
                Treasure tr = (Treasure) g.getBoard().getElementAt(pos[1],pos[0]);
    
                sendMessage("203 MOVE OK TRES "+tr.getTreasureValue());
                broadcastInGame("511 "+p.getName()+" POS "+pos[1]+" "+pos[0]+" TRES "+tr.getTreasureValue(),g.getGameId());     //missing treasure value
    
                tr.setTreasureValue(0);
                g.getBoard().setElementAt(null,pos[1],pos[0]);
                if (g.getBoard().getTreasureCount() == 0) {
                    broadcastInGame("530 " + g.leadingPlayer().getName() + " WINS", g.getGameId());
                }
    
            } else if(ret.equals("Hole")) {
                sendMessage("666 MOVE HOLE DEAD");
                broadcastInGame("520 "+p.getName()+" DIED",g.getGameId());
                p.setMoney(0);
                p.killPlayer();
                if (g.areAllPlayersDead()) {
                    broadcastInGame("600 GAME OVER", g.getGameId());
                }
            }
        }
	}
}