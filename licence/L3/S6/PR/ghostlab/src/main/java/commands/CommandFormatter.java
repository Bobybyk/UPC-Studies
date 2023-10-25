package main.java.commands;

import main.java.console.DebugLogger;
import main.java.console.DebugType;

public class CommandFormatter {
	
	private static byte[] getByteArray(String args[]) {
		byte[] format = new byte[getSize(args)];

		int index = 0;

		if (args.length == 1) {
			format = (args[0] + "***").getBytes();
			return format;
		}

		for(int i=0;i<args.length;i++) {

			byte[] argBytes = args[i].getBytes(); 

			if(i == (args.length -1)) {
				try {
					format[index++] = (byte) Integer.parseInt(args[i]);		//to change if we want to handle integers greater than 1 byte
					break;
				} catch (NumberFormatException e) {
					
					DebugLogger.print(DebugType.ERROR, "CommandFormatter : L'argument numéro " + i + "ne représente pas une valeur numérique");
				}

			}

			for(int j=0;j<args[i].length();j++)
				format[index++] = argBytes[j];

			format[index++] = (byte) ' ';
		}

		return format;
	}

	public static byte[] formatForTCP(String[] args) {

		byte[] message = getByteArray(args);

		for(int i=(message.length - 3);i<message.length;i++) {
			message[i] = (byte) '*';
		}

		// affichage message formaté/déformaté pour debug
		String messageDebug = "";
		for (int i = 0; i < message.length; i++) {
			messageDebug += (char)message[i];
		}
		DebugLogger.print(DebugType.FORMAT, "MESSAGE FORMATED : " + messageDebug);

		return message;
	}

	private static int getSize(String args[]) {
		int size = 0;

		for(int i=0;i<args.length;i++) {

			for(int j=0;j<args[i].length();j++) {
				size++;
			}

			if(i == (args.length - 1)) {		//if the last argument was handled
				size += 3;					//add size for terminating characters
				break;
			} 

			size++;
		}

		return size;
	}

}