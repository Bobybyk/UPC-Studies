package main.java.console;

import java.util.HashMap;
import java.util.Scanner;

import main.java.client.Client;
import main.java.client.ClientTCP;
import main.java.commands.Command;
import main.java.commands.CommandDev;
import main.java.commands.CommandTCP;
import main.java.commands.dev.*;
import main.java.commands.out.tcp.CommandAskTcpAvailableGames;
import main.java.commands.out.tcp.CommandAskTcpCreate;
import main.java.commands.out.tcp.CommandAskTcpDropGame;
import main.java.commands.out.tcp.CommandAskTcpJoin;
import main.java.commands.out.tcp.CommandAskTcpMapSize;
import main.java.commands.out.tcp.CommandAskTcpMoveDown;
import main.java.commands.out.tcp.CommandAskTcpMoveLeft;
import main.java.commands.out.tcp.CommandAskTcpMoveRight;
import main.java.commands.out.tcp.CommandAskTcpMoveUp;
import main.java.commands.out.tcp.CommandAskTcpPlayerGame;
import main.java.commands.out.tcp.CommandAskTcpPlayersInGame;
import main.java.commands.out.tcp.CommandAskTcpSendMessage;
import main.java.commands.out.tcp.CommandAskTcpSendMessageAll;
import main.java.commands.out.tcp.CommandAskTcpStart;
import main.java.commands.out.tcp.CommandAskTcpUnregister;

public class Console implements Runnable {
    private static ClientTCP clientTCP;

    private static HashMap<String, Command> commandList = new HashMap<>();

    public static void connectConsole(ClientTCP clientTcp) {
        clientTCP = clientTcp;

        // remplissage de la liste des commandes TCP fonctionnelles
        commandList.put("GAME?", new CommandAskTcpAvailableGames(clientTcp.getPrintWriter()));
        commandList.put("NEWPL", new CommandAskTcpCreate(clientTcp.getPrintWriter()));
        commandList.put("REGIS", new CommandAskTcpJoin(clientTcp.getPrintWriter()));
        commandList.put("SIZE?", new CommandAskTcpMapSize(clientTcp.getPrintWriter()));
        commandList.put("LIST?", new CommandAskTcpPlayerGame(clientTcp.getPrintWriter()));
        commandList.put("START", new CommandAskTcpStart(clientTcp.getPrintWriter()));
        commandList.put("UNREG", new CommandAskTcpUnregister(clientTcp.getPrintWriter()));

        commandList.put("UPMOV", new CommandAskTcpMoveUp(clientTcp.getPrintWriter()));
        commandList.put("DOMOV", new CommandAskTcpMoveDown(clientTcp.getPrintWriter()));
        commandList.put("LEMOV", new CommandAskTcpMoveLeft(clientTcp.getPrintWriter()));
        commandList.put("RIMOV", new CommandAskTcpMoveRight(clientTcp.getPrintWriter()));
        commandList.put("IQUIT", new CommandAskTcpDropGame(clientTcp.getPrintWriter()));
        commandList.put("GLIS?", new CommandAskTcpPlayersInGame(clientTcp.getPrintWriter()));

        commandList.put("SEND?", new CommandAskTcpSendMessage(clientTcp.getPrintWriter()));
        commandList.put("MALL?", new CommandAskTcpSendMessageAll(clientTcp.getPrintWriter()));
        
    }

    public static void createConsole() {
        // remplissage de la liste avec des commandes developpeur
        commandList.put("debug", new CommandDebug());
        commandList.put("help", new CommandHelp());
        commandList.put("kill", new CommandKill());
        commandList.put("killclient", new CommandKillClient());
        commandList.put("gameinfo", new CommandGameInfo());
        commandList.put("historical", new CommandListHistorical());

        // remplissage de la liste des commandes tcp non fonctionnelles
        commandList.put("GAME?", new CommandAskTcpAvailableGames(null));
        commandList.put("NEWPL", new CommandAskTcpCreate(null));
        commandList.put("REGIS", new CommandAskTcpJoin(null));
        commandList.put("SIZE?", new CommandAskTcpMapSize(null));
        commandList.put("LIST?", new CommandAskTcpPlayerGame(null));
        commandList.put("START", new CommandAskTcpStart(null));
        commandList.put("UNREG", new CommandAskTcpUnregister(null));

        commandList.put("UPMOV", new CommandAskTcpMoveUp(null));
        commandList.put("DOMOV", new CommandAskTcpMoveDown(null));
        commandList.put("LEMOV", new CommandAskTcpMoveLeft(null));
        commandList.put("RIMOV", new CommandAskTcpMoveRight(null));
        commandList.put("IQUIT", new CommandAskTcpDropGame(null));
        commandList.put("GLIS?", new CommandAskTcpPlayersInGame(null));

        commandList.put("SEND?", new CommandAskTcpSendMessage(null));
        commandList.put("MALL?", new CommandAskTcpSendMessageAll(null));

        new Thread(new Console()).start();
    }

    @Override
    public void run() {

        try (Scanner sc = new Scanner(System.in)) {
            layout();
            while(true) {
                // on collecte l'input console à traiter
                useMessage(sc.nextLine());
            }
        }
    }

    public static void layout() {
        System.out.print("\u001B[31m");
        System.out.print("ghostlab_debug> ");
        System.out.print("\u001B[37m");
    }


    // parsing de la commande et exécution de la fonction associée
    public static void useMessage(String strCommand) {
        
        // si la commande est vide, on ne fait rien
        if (strCommand.equals("")) {
            layout();
            return;
        }

        // on récupère la commande sous forme d'arguments
        String[] args = breakCommand(strCommand);

        // on stock l'instance de la commande associée dans une variable Command
        Command command = commandList.get(args[0]);

        if (command instanceof CommandTCP) {
            if (Client.isConnected) {
                ((CommandTCP) command).execute(clientTCP, args);
            } else {
                DebugLogger.print(DebugType.WARNING, "[ATTENTION/CONSOLE] : Vous n'êtes pas connecté à un serveur, impossible d'utiliser cette commande");
            }
        }

        else if (command instanceof CommandDev) {
            layout();
            ((CommandDev) command).execute(args);
        }

        else {
            DebugLogger.print(DebugType.CONFIRM, "UNKNOWN command");
        }
        
    }

    private static String[] breakCommand(String command) {
        return command.split(" ");
	}
    
}
