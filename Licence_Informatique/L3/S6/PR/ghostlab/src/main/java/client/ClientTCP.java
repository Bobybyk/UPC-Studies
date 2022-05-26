package main.java.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.LinkedList;

import main.java.commands.CommandTCP;
import main.java.commands.in.tcp.CommandRcvMultTcpWelco;
import main.java.commands.in.tcp.CommandRcvTcpBye;
import main.java.commands.in.tcp.CommandRcvTcpDunno;
import main.java.commands.in.tcp.CommandRcvTcpGameInfo;
import main.java.commands.in.tcp.CommandRcvTcpJoinNO;
import main.java.commands.in.tcp.CommandRcvTcpJoinOK;
import main.java.commands.in.tcp.CommandRcvTcpMapSize;
import main.java.commands.in.tcp.CommandRcvTcpNbrGames;
import main.java.commands.in.tcp.CommandRcvTcpNbrPlayersInGame;
import main.java.commands.in.tcp.CommandRcvTcpNewPos;
import main.java.commands.in.tcp.CommandRcvTcpNewPosPoints;
import main.java.commands.in.tcp.CommandRcvTcpPlayerGame;
import main.java.commands.in.tcp.CommandRcvTcpPlayerId;
import main.java.commands.in.tcp.CommandRcvTcpPlayerPos;
import main.java.commands.in.tcp.CommandRcvTcpPlayersPosScoreInGame;
import main.java.commands.in.tcp.CommandRcvTcpUnregisterOK;
import main.java.commands.in.tcp.CommandRcvTcpSend;
import main.java.commands.in.tcp.CommandRcvTcpNSend;
import main.java.console.Console;
import main.java.console.DebugLogger;
import main.java.console.DebugType;
import main.java.gui.controller.ControlGUI;
import main.java.game.GameInfo;


public class ClientTCP extends Thread {
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private ControlGUI gui;
    private boolean multicastCreated;

    private HashMap<String,CommandTCP> commandRcvTcpList = new HashMap<String,CommandTCP>();
    
    public static boolean clientTCPCreated = false;

    public ClientTCP(String ip, int port, ControlGUI gui) {
        try {
            DebugLogger.print(DebugType.COM, "Création de la connection TCP avec le serveur...");
            this.clientSocket = new Socket(ip, port);
            this.out = new PrintWriter(clientSocket.getOutputStream());
            this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            this.gui = gui;

            // remplissage de la liste de commandes recevables
            commandRcvTcpList.put("DUNNO", new CommandRcvTcpDunno(out));
            commandRcvTcpList.put("OGAME", new CommandRcvTcpGameInfo(out));
            commandRcvTcpList.put("REGNO", new CommandRcvTcpJoinNO(out));
            commandRcvTcpList.put("REGOK", new CommandRcvTcpJoinOK(out));
            commandRcvTcpList.put("SIZE!", new CommandRcvTcpMapSize(out));
            commandRcvTcpList.put("GAMES", new CommandRcvTcpNbrGames(out));
            commandRcvTcpList.put("LIST!", new CommandRcvTcpPlayerGame(out));
            commandRcvTcpList.put("PLAYR", new CommandRcvTcpPlayerId(out));
            commandRcvTcpList.put("UNROK", new CommandRcvTcpUnregisterOK(out));
            commandRcvTcpList.put("WELCO", new CommandRcvMultTcpWelco(out));
            commandRcvTcpList.put("POSIT", new CommandRcvTcpPlayerPos(out));
            commandRcvTcpList.put("MOVE!", new CommandRcvTcpNewPos(out));
            commandRcvTcpList.put("MOVEF", new CommandRcvTcpNewPosPoints(out));
            commandRcvTcpList.put("GOBYE", new CommandRcvTcpBye(out));
            commandRcvTcpList.put("GLIS!", new CommandRcvTcpNbrPlayersInGame(out));
            commandRcvTcpList.put("GPLYR", new CommandRcvTcpPlayersPosScoreInGame(out));
            commandRcvTcpList.put("SEND!", new CommandRcvTcpSend(out));
            commandRcvTcpList.put("NSEND", new CommandRcvTcpNSend(out));

            DebugLogger.print(DebugType.COM, "...succès");

            clientTCPCreated = true;
            multicastCreated = false;
        } catch (UnknownHostException e) {
            DebugLogger.print(DebugType.ERROR, "[ClientTCP/ERREUR] : L'adresse IP de l'hôte ne peut être déterminée");
        } catch (IOException e) {
            DebugLogger.print(DebugType.ERROR,"[ClientTCP/ERREUR] : Numero de PORT INDISPONIBLE ou IP INCONNUE");
        }

    }

    @Override
    public void run() {

        DebugLogger.print(DebugType.CONFIRM, "Début de l'écoute TCP");
        
        // défini si le traitement de la commande doit être ignoré ou non
        boolean ignore = false;

        // tant que le socket est connecté
        while(Client.isConnected) {
            
            try {

                if (!multicastCreated && GameInfo.portMulticast != -1 && GameInfo.ipMulticast != "") {
                    new Multicast(gui).start(); 
                    multicastCreated = true;
                }

                // liste stockant la commande, caractère par caracère, sous sa forme entière (0-65535)
                LinkedList<Integer> serverMsg = new LinkedList<>();

                // compte le nombre d'étoiles lu (caractère de fin de message)
                int offsetLimiter = 0;
                int readVal = 0;

                // tant que le caractère de fin de message (3 étoiles) n'est pas lu, on ajoute les caractères à la liste
                while(offsetLimiter < 3) {

                    // lit le premier caractère sur le buffer
                    readVal = in.read();

                    // si le socket est déconnecté : on arrête de lire et on ne traite pas la commande
                    if (readVal == -1) {
                        DebugLogger.print(DebugType.CONFIRM, "Server is closed : disconnected");
                        System.out.println("");
                        Console.useMessage("killclient");
                        ignore = true;
                        break;
                    }

                    // ajoute le caractère à la liste
                    serverMsg.add(readVal);

                    /* si la valeur ascii du caractère lu équivaut à une étoile, on incrémente le compteur, 
                     * sinon on le réinitialise à 0 car on déduit que les étoiles précédemment lu ne sont pas des délimiteurs de fin de message
                     */
                    if (readVal == 42) {
                        offsetLimiter++;
                    } else {
                        offsetLimiter = 0;
                    }
                }

                // si la variable d'ignorance est à false, on traite la commande
                if (!ignore) {
                    useMessage(serverMsg);
                }

            } catch(IOException e) {
                DebugLogger.print(DebugType.CONFIRM, "Socket closed : disconnected");
            }
        }

        try {
            clientSocket.close();
            clientTCPCreated = false;
            Client.disconnect();
            gui.actualise();
        } catch (IOException e) {
            DebugLogger.print(DebugType.ERROR, "la fermeture du socket client n'a pas aboutie");
            System.exit(1);
        }

    }

    // envoie du message sur la sortie vers le serveur
	public void sendMessage(String message) {
        out.println(message);
        out.flush();
    }

    // parsing de la commande
	public void useMessage(LinkedList<Integer> command) {
        
        String commandName = "";

        // on récupère l'en-tête de la commande (son premier argument) pour sélectionner l'exécution associée
        for (int i = 0; i < 5; i++) {
            commandName += (char) command.get(i).byteValue();
        }
		
        // on parcourt la liste de commandes pour trouver la commande correspondante
		for(String s : commandRcvTcpList.keySet()) {

			if(s.equals(extractFirst(commandName))) {

                // appel de la fonction dans l'instance de la classe associée à la commande
				commandRcvTcpList.get(s).execute(this, command);

                // actualisation de l'interface graphique
                gui.actualise();

                // on arrête de parcourir la liste des commandes
                return;
			}
		}

        
	}

    private String extractFirst(String str) {
        String first = "";
        if (str.length() >= 5) {
            for (int i = 0 ; i < 5 ; i++) {
                first += str.charAt(i);
            }
        }
        return first;
        
    }

    public PrintWriter getPrintWriter() {
        return this.out;
    }

    public OutputStream getOutputStream() throws IOException{
        return this.clientSocket.getOutputStream();
    }

    public BufferedReader getBufferedReader() {
        return this.in;
    }


    public synchronized void setGUI(ControlGUI gui) {
        this.gui = gui;
    }
}
