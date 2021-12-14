package client.commands.in;

import java.io.PrintWriter;
import java.util.Arrays;

import client.Player;
import client.GameInfo;
import client.commands.Command;

import client.view.graphicDisplay.ControlDisplay;
import client.control.UIBis.DirectionalCrosses;
import client.control.UI.VueSwing;

import server.elements.*;

import java.util.LinkedList;

public class CommandGameStart extends Command {
	private PrintWriter out;

	public CommandGameStart(PrintWriter out) {
		super("153",out);
		this.out = out;
	}

	public void execute(Player p, String[] args) {
		sendMessage("420 GETWALLS");
		sendMessage("410 GETTREASURES");
		sendMessage("400 GETHOLES");
        GameInfo.setStarted(true);


		new DirectionalCrosses(new ControlDisplay(p), this.out);
		new VueSwing();
  	}
}