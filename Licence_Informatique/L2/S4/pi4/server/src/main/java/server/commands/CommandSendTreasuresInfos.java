package server.commands;

// import java Classes
import java.io.PrintWriter;

// import our Classes
import server.playingProps.Game;
import server.playingProps.Player;
import server.ServerMain;


public class CommandSendTreasuresInfos extends Command {

	public CommandSendTreasuresInfos(PrintWriter pw) {
		super("411",pw);
	}

	@Override
	public void execute(Game g, Player p, String[] args) {
		int k = g.getBoard().getTreasureCount();        //initialize number of treasures
        int[][] b = g.getBoard().getTreasurePos();      //get an 

        sendMessage("411 NUMBER "+k);              //send number of treasures

        int index = 0;
        String toSend = ""; 

        int stop = k%5 == 0 ? (k/5) : (k/5) + 1;        //initialize the number of lines needed 

        for(int i=1;i<=stop;i++) {                      //this loop executes the code "stop" times
            toSend = "411 MESS "+i+" POS";              //prepare the line
            int count = 0;
            for(int j=index;j<b.length;j++) {           //browse the array of array
                index = j;
                if(count == 5)                          //if 5 couples of coordinates have been sent
                    break;
                for(int l=0;l<b[j].length;l++) {        //browse each tile of the array
                    toSend += " "+b[j][l];              //add the coordinates to the line
                }
                count++;
            }
            sendMessage(toSend);                   // send each line
        }
	}
}