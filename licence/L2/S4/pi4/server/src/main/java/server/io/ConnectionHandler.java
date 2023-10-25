package server.io;

// import java Classes
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;

// import our Classes
import server.ServerMain;
import server.playingProps.Player;
import server.io.*;
import server.io.Console;


public class ConnectionHandler implements Runnable{

	Vector<Player> users = ServerMain.connectedUsers;
	Vector<Communication> coms = ServerMain.launchedCom;
	ServerSocket serverSoc;

	private int port;
	private String configFilePath;

	public ConnectionHandler(int port, String configFilePath) {
		this.port = port;
		this.configFilePath = configFilePath;
	}

	public void run() {
		System.out.println("Config file loaded: "+this.configFilePath);
		System.out.println("Server launched on port "+this.port);
		try {		
			serverSoc = new ServerSocket(this.port);	//opening the server
			Socket client;					
			while(ServerMain.isRunning()) {			//as long as the server is running
					client = serverSoc.accept();		//waiting for connection
					BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));


					String[] received = breakCommand(in.readLine());		//we receive an input from the client
					Player sc = null;
					Communication com = null;
					if(received[0].equals("100")) {							//if the received command is "100" (which means the client sends its name)
						sc = new Player(client,received[3]);		//build the client manager 
						com = new Communication(sc);
					}

					System.out.println(com.getName()+" is now connected");	//print in the server console
					Thread c = new Thread(com);

					users.add(sc);		//add the client to the list
					coms.add(com);		//add the communication to the list
					c.start();
			}
		} catch (IOException e) {
			System.out.println("ServerSocket closed by server");
		}
	}

	public void stop() {
		try {
			serverSoc.close();		//this line closes the ServerSocket cutting the execution of the accept() method
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	private String[] breakCommand(String command) {			//This method breaks the command which arguments are separated by spaces
		String delims = "[ ]+";		//This line sets the delimiter between words. Here we use "space" as delimiter, brackets indicate 
									//the start and end of the group. "+" indicate that conscutive delimitor should be treated as a single one
		String[] args = command.split(delims);
		return args;
	}
}