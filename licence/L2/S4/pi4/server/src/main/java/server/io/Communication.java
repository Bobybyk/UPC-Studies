package server.io;

// import java Classes
import java.net.Socket;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

import server.playingProps.Player;
import server.playingProps.Game;

// import our Classes
import server.ServerMain;

import server.elements.Treasure;
import server.elements.Element;

import server.commands.*;

public class Communication implements Runnable {
    private Player _player;
    private String username;
    boolean isConnected;
    Socket s;
    BufferedReader in;
    PrintWriter out;

    private HashMap<String,Command> commandList = new HashMap<String,Command>();

    public Communication(Player p) {
        this._player = p;
        this.s = p.getSocket();
        this.username = p.getName();
        
        /**
         * construction of the communication with the client
         * in for the buffer which read inputs from the client
         * out for the writer which will send requests to the client
         */

        try {
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new PrintWriter(s.getOutputStream());
        } catch( IOException e) {
            e.printStackTrace();
        }

        /*
         *  Filling the hashmap that will be used to recognize the commands
         */

        commandList.put("0",new CommandStopServer(out));
        commandList.put("110",new CommandCreateGame(out));
        commandList.put("120",new CommandSendGameInfo(out));
        commandList.put("130",new CommandJoinGame(out));
        commandList.put("150",new CommandRequestStart(out));
        commandList.put("152",new CommandRequestStartResponse(out));
        commandList.put("200",new CommandPlayerMovement(out));
        commandList.put("400",new CommandSendHolesInfos(out));
        commandList.put("410",new CommandSendTreasuresInfos(out));
        commandList.put("420",new CommandSendWallsInfos(out));
        commandList.put("501",new Command501(out));
        commandList.put("512",new CommandUpdateRoundManager(out));
        commandList.put("521",new CommandUpdateRoundManager(out));

        new Command101(out).execute(null,p,null);
    }

    @Override
    public void run() {

        /**
         * as long as the remote socket is connected :
         *  read the input
         *  print it
         *  print if user is disconected
         */

        while(ServerMain.isRunning()) {
            try {
                String msg = in.readLine(); 
                if(!ServerMain.isRunning() || msg == null) {
                    System.out.println(this.username+" disconnected !");
                    break;
                }
                System.out.println(this.username+" wrote: "+ msg);
                useMessage(msg + " " + _player);
                
            } catch(IOException e) {
                System.out.println(this.username + (": socket closed by the server."));
                ServerMain.stop();
            }
        }
        this.isConnected = false;   //update status
    }

    private void useMessage(String command) {

        /**
         * definition of important vars for commands treatment, as :
         * brokenCommand (tab of String with each command arguments)
         * g (a Game type with the current game)
         * p (a Player type with the current player) 
         */

        String[] brokenCommand = command.split("[ ]+");
        Game g = getPlayer().getGameConnectedTo();
        Player p = getPlayer();

        for(String s : commandList.keySet()) {
            if(s.equals(brokenCommand[0])) {
                commandList.get(s).execute(g,p,brokenCommand);
                return;
            }
        }
        System.out.println("Unrecognized command");
    }

    public void stop() {
        try {
            this.s.close();         //This line closes the socket s which unblocks the execution of run()
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Player getPlayer() {
        return this._player;
    }
    public String getName() {
        return this._player.getName();
    }
    public PrintWriter getOutput() {
        return this.out;
    }
}
