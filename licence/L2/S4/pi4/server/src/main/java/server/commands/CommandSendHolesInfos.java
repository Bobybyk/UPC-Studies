package server.commands;

// import java Classes
import java.io.PrintWriter;

// import our Classes
import server.playingProps.Game;
import server.playingProps.Player;
import server.ServerMain;


public class CommandSendHolesInfos extends Command {

	public CommandSendHolesInfos(PrintWriter pw) {
		super("401",pw);
	}

	@Override
	public void execute(Game g, Player p, String[] args) {
		int k = g.getBoard().getHoleCount();
        int[][] b = g.getBoard().getHolePos();

        sendMessage("401 NUMBER "+k);

        int index = 0;
        String toSend = "";

        int stop = k%5 == 0 ? (k/5) : (k/5) + 1;

        for(int i=1;i<=stop;i++) {
            toSend = "401 MESS "+i+" POS";
            int count = 0;
            for(int j=index;j<b.length;j++) {
                index = j;
                if(count == 5)
                    break;
                for(int l=0;l<b[j].length;l++) {
                    toSend += " "+b[j][l];      
                }
                count++;
            }
            sendMessage(toSend);
        }
	}
}