package client;

// import java Classes
import java.net.Socket;
import java.io.IOException;
import java.lang.InterruptedException;
import java.lang.Thread;
import java.io.FileReader;
import java.io.File;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

// import our Classes
import client.connex.Communication;
import client.control.shell.Console;
import client.view.graphicDisplay.GameSelectionDisplay;
import client.control.UIBis.GameManager;
import server.elements.*;
import client.ServerInfo;
import client.view.graphicDisplay.Menu;
import client.control.UIBis.MenuManager;


public class Player {

    private static String username;
    private static File configFile;
    private static String serverIP;
    private static int serverPort;
    private static Player p;
    private static FileReader reader;
    private static Socket s;
    private static int gameID;

    public static boolean isConnected = true;
    
    public Player() {
        this.username = ServerInfo.getName();
        this.serverIP = ServerInfo.getIp()[0] + "." + ServerInfo.getIp()[1] + "." + ServerInfo.getIp()[2] + "." + ServerInfo.getIp()[3];
        this.serverPort = new Integer(ServerInfo.getPort());    
    }

    public Socket getSocket() {
        return s;
    }

    public int getGameId() {
        return gameID;
    }

    public void setGameId(int id) {
        gameID = id;
    }

    public static boolean isConnected() {
        return isConnected;
    }

    public static String getName() {
        return username;
    }

    public static void printGameBoard() {
        char[][] board = new char[GameInfo.getMap()[0]][GameInfo.getMap()[1]];
        for(int i=0;i<board.length;i++)
            for(int j=0;j<board[i].length;j++)
                board[i][j] = '.';

        for(Wall w : GameInfo.getWalls()) {
            int x = w.getX();
            int y = w.getY();
            board[x][y] = 'W';
        }
        for(Treasure t : GameInfo.getTreasures()) {
            int x = t.getX();
            int y = t.getY();
            board[x][y] = 'T';
        }
        for(Hole h : GameInfo.getHoles()) {
            int x = h.getX();
            int y = h.getY();
            board[x][y] = 'H';
        }

        for(String s : GameInfo.getPlayerCoordinates().keySet()) {
            int x = GameInfo.getPlayerCoordinates().get(s)[0];
            int y = GameInfo.getPlayerCoordinates().get(s)[1];
            board[y][x] = s.toLowerCase().charAt(0);
        }

        for(int i=0;i<board.length;i++){
            System.out.print("W ");
        }
        System.out.println();

        for(int i=1;i<board.length-1;i++) {
            System.out.print("W ");
            for(int j=1;j<board[i].length-1;j++) {
                System.out.print(board[i][j]+" ");
            }
            System.out.print("W");
            System.out.println();
        }

        for(int i=0;i<board.length;i++){
            System.out.print("W ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        
        Menu menu = new Menu();
        Thread t = new Thread(new Runnable() { 
            public void run() {
                try {
                    p = new Player();
                    s = new Socket(serverIP,serverPort);
        
                    Communication com = new Communication(p);
                    Console cons = new Console(com);
        
                    Thread communication = new Thread(com);
                    Thread console = new Thread(cons);
        
                    communication.start();
                    console.start();
                    
                    menu.dispose();

                    new GameManager(new GameSelectionDisplay(), com.getOutput(), p, cons);
                    
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
	    });

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MenuManager(menu, t);   
                menu.setVisible(true);
            } 
        }); 
    }

}
