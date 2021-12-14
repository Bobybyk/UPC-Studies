package server.playingProps;

// import java Classes
import java.net.Socket;
import java.io.IOException;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Random;


// import our Classes
import server.elements.*;
import server.io.Communication;
import server.playingProps.Game;
import server.playingProps.Board;
import server.ServerMain;


public class Player {
    //player attributes

    private static int globalId = 0;

    private final int playerID;
    private String username;
    private int money;

    private int posX;
    private int posY;
    private boolean isDead;
    private int gameID = -1;
    private boolean ready;

    private boolean answered = false;

    private String _msg;

    //Network attributes
    Socket s;
    
    //CONSTRUCTOR
    public Player(Socket s, String name) {
    	this.username = name;
    	this.isDead = false;
        this.s = s;
        this._msg = "";
        this.ready = false;
        this.playerID = globalId;
        globalId++;
    }

    //GAME MANAGEMENT METHODS
    public void setGame(Game g) {
        if(this.gameID == g.getGameId())
            System.out.println("The player is already connected to this game");
        else
            this.gameID = g.getGameId();
    }
    public void leaveGame() {
        if(this.gameID != -1) {
            this.gameID = -1;
            if(this.getGameConnectedTo().removePlayer(this))
                System.out.println("The player successfully left the game");
        }
        else
            System.out.println("This player isn't currently in any game");
    }


    //Player management methods
    protected void addMoney(int amount) {
    	this.money += amount;
    }

    public void setStartingPos(Board b){ //initiate beginning positions randomly for players
    	Random rand = new Random();
    	int[] pos = {-1,-1};
    	do{
    	    pos[0] = rand.nextInt(b.getSizeY()-2)+ 1; 
    	    pos[1] = rand.nextInt(b.getSizeX()-2)+ 1;
         } while(b.getElementAt(pos[1],pos[0]) != null);/*keep going until the starting location isn't a special item */
    	this.setPos(b, pos);
    }

    public String setPos(Board b, int[] tab) {
    	if(b.getElementAt(tab[1], tab[0]) instanceof Wall) {
    		return "Wall";
    	}
    	this.posX = tab[0];
    	this.posY = tab[1];
    	if(b.getElementAt(tab[1], tab[0]) instanceof Hole){ //Added this directly in this function to avoid overencumber the run method in 'Game'
    	    this.killPlayer();
            return "Hole";
    	}
    	if((b.getElementAt(tab[1], tab[0]) instanceof Treasure)){//The player steps on a treasure, the content is added to his money and the treasure is emptied
    	    Treasure tmp = (Treasure)b.getElementAt(tab[1], tab[0]);
    	    this.addMoney(tmp.getTreasureValue());
            return "Treasure";
    	}
    	return "ok";
    }

    protected void setPosFromInput(Board b, int[] currentPos){
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Move: Up(u),Right(r),Left(l),Down(d)");
    	if(sc.nextLine().equals("u")){
    	    currentPos[0] -= 1;
    	    this.setPos(b, currentPos);
    	}
    	if(sc.nextLine().equals("r")){
    	    currentPos[1] += 1;
    	    this.setPos(b, currentPos);
    	}
    	if(sc.nextLine().equals("l")){
    	    currentPos[1] -= 1;
    	    this.setPos(b, currentPos);
    	}
    	if(sc.nextLine().equals("d")){
    	    currentPos[0] += 1;
    	    this.setPos(b, currentPos);
    	}
    }

    public void killPlayer() {
    	isDead = true;
    }

    //GETTERS
    public String getName() {       //returns the name of the class instance
        return username;
    }
    public boolean getAnswered() {
        return this.answered;
    }
    public boolean isPlayerDead() {
        return isDead;
    }
    public int[] getPos() {
        int[] tab = {posX,posY};
        return tab;
    }
    public Socket getSocket() {
        return this.s;
    }
    public String getMsg() {
        return this._msg;
    }
    public int getGameId() {
        return this.gameID;
    }
    public int getPlayerId() {
        return this.playerID;
    }
    public String getUserName() {
        return this.username;
    }
    public boolean getReady() {
        return this.ready;
    }
    public int getMoney(){
        return this.money;
    }
    public Game getGameConnectedTo() {
        for(Game g : ServerMain.createGames) {
            if(g.getGameId() == this.gameID)
                return g;
        }
        return null;
    }

    //SETTERS
    public void setUserName(String s) {
        this.username = s;
    }
    public void setAnswered(boolean b) {
        this.answered = b;
    }
    public void setGameId(int id) {
        this.gameID = id;
    }
    public void setReady(boolean b) {
        this.ready = b;
    }
    public void setMoney(int n){
        this.money = n;
    }

    //TOSTRING
    public String toString() {
        return this.username + " ["+this.posX+","+this.posY+"] "+money+"$ "+isDead;
    }
}
