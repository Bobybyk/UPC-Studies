package client.connex;

// import java Classes
import java.util.Scanner;
import java.net.Socket;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

// import our Classes
import client.Player;
import client.GameInfo;
import client.control.shell.Console;
import client.view.graphicDisplay.ControlDisplay;
import client.control.UIBis.DirectionalCrosses;
import client.commands.Command;
import client.commands.in.*;


public class Communication implements Runnable{

    Socket s;
    BufferedReader in;
    PrintWriter out;
    Player p;
    String serverMsg = "";

    private HashMap<String,Command> commandList = new HashMap<String,Command>();


    public Communication(Player p) {
        this.p = p;
    	this.s = p.getSocket();
    	try {
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new PrintWriter(s.getOutputStream());
        } catch( IOException e) {
            e.printStackTrace();
        }

        commandList.put("121",new CommandUpdateMapList(out));
        commandList.put("131",new CommandMapJoined(out));
        commandList.put("152",new CommandRequestStart(out));
        commandList.put("153",new CommandGameStart(out));
        commandList.put("201",new CommandMoveOk(out));
        commandList.put("202",new CommandWallMet(out));
        commandList.put("203",new CommandWalkedOnTreasure(out));
        commandList.put("401",new CommandUpdateHoles(out));
        commandList.put("411",new CommandUpdateTreasures(out));
        commandList.put("421",new CommandUpdateWalls(out));
        commandList.put("500",new CommandTurnBroadcast(out));
        commandList.put("510",new CommandPlayerPosBroadcast(out));
        commandList.put("511",new CommandPlayerTreasurePosBroadcast(out));
        commandList.put("520",new CommandPlayerDied(out));
        commandList.put("600",new CommandEveryoneDead(out));
        commandList.put("666",new CommandMoveHoleDead(out));
        commandList.put("902",new CommandNotYourTurn(out));

    }

	public void sendMessage(String message) {        //This method sends a message to the client handled by the instance of the class
        out.println(message);
        out.flush();
    }

    public Player getPlayer() {
        return this.p;
    }

    public PrintWriter getOutput() {
        return this.out;
    }

	@Override
    public void run() {
        try {
        	while(Player.isConnected()) {
            	serverMsg = in.readLine();
        		if(serverMsg == null) {       // if the socket is disconnected
                	System.out.println("Disconnected !");
                	Player.isConnected = false;
                	break;
            	}
            	System.out.println("Server wrote: "+ serverMsg);
                useMessage(serverMsg);
        	}
        } catch(IOException e) {
            System.out.println("Socket closed by the server.");
        }
    }

	public void useMessage(String command) {
        String[] args = breakCommand(command);
		
		for(String s : commandList.keySet()) {
			if(s.equals(args[0])) {
				commandList.get(s).execute(p,args);
                return;
			}
		}
	}

	private String[] breakCommand(String command) {			//This method breaks the command which arguments are separated by spaces
		String delims = "[ ]+";		//This line sets the delimiter between words. Here we use "space" as delimiter, brackets indicate 
									//the start and end of the group. "+" indicate that conscutive delimitor should be treated as a single one
		String[] args = command.split(delims);
		return args;
	}

}
