package client.commands.in;

import java.io.PrintWriter;

import client.Player;
import client.GameInfo;
import client.control.shell.Console;
import client.commands.Command;

public class CommandRequestStart extends Command {
	
	public CommandRequestStart(PrintWriter out) {
		super("152",out);
	}

	public void execute(Player p, String[] args) {
		System.out.println("Ready to Play ? y/N : ");
		Console.setStartRequested(true);
	}
}