package main.java.commands.dev;

import main.java.client.Client;
import main.java.commands.CommandDev;
import main.java.console.DebugLogger;
import main.java.console.DebugType;

public class CommandKillClient extends CommandDev {

    @Override
    public void execute(String[] args) {
        DebugLogger.print(DebugType.CONFIRM, "COMMAND : killclient");

        if (Client.isConnected) {
            Client.disconnect();
        } else {
            DebugLogger.print(DebugType.WARNING, "[ATTENTION/CommandKillClient] Le client n'est connecté à aucun serveur");
        }
        
    }
    
}
