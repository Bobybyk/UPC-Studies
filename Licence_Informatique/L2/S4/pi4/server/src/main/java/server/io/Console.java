package server.io;

// import java Classes
import java.util.Scanner;
import java.util.Arrays;

// import our Classes
import server.io.*;
import server.io.Communication;
import server.playingProps.Game;
import server.playingProps.Player;
import server.elements.Treasure;


public class Console implements Runnable {
	private Communication _com;
	private String _message;

	public Console(Communication com) {
		this._com = com;
		this._message = "";
	}

	@Override
	public void run() {
	}
}
