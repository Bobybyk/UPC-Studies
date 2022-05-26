package main.java.commands.dev;

import main.java.client.Client;
import main.java.commands.CommandDev;
import main.java.console.DebugLogger;
import main.java.console.DebugType;

public class CommandKill extends CommandDev {

    @Override
    public void execute(String[] args) {
        DebugLogger.print(DebugType.CONFIRM, "COMMAND : kill program");

        if (Client.isConnected) {
            Client.disconnect();
        }

        System.exit(0);
    }
    
}
