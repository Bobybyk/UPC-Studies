package main.java.client;

import main.java.console.Console;
import main.java.console.DebugLogger;
import main.java.console.DebugType;
import main.java.game.GameInfo;
import main.java.gui.Frame;
import main.java.gui.controller.ControlGUI;

public class Client {
    private ClientTCP clientTCP;
    private ClientUDP clientUDP;

    public static boolean isConnected = false;

    public Client(String ip, int port, ControlGUI gui) {
        this.clientTCP = new ClientTCP(ip, port, gui);

        if (ClientTCP.clientTCPCreated) {
            this.clientUDP = new ClientUDP(ip, gui);
            Console.connectConsole(clientTCP);
        }
        
        if (ClientTCP.clientTCPCreated && ClientUDP.clientUDPCreated) {
            Client.isConnected = true;
            this.clientTCP.start();
            this.clientUDP.start();
        }
    }
    public static void main(String[] args) throws InterruptedException {

        // initialisation des types de debug
        DebugLogger.setTypeMap();
        Client client;

        // création de la console avec les commandes de debog uniquement
        Console.createConsole();

        Thread.sleep(50);

        // vérification que le nombre de paramètres donnés au programme n'excède pas le max attendu
        if (args.length > 3) {
            DebugLogger.print(DebugType.WARNING, "[ATTENTION] : " + args.length + " paramètres donnés alors que seulement 3 max attendus... Erreurs potentielles (voir README.md)");
        }

        /* si le nombre de paramètres minimaux (pour permettre une connexion direct au serveur) 
         * n'est pas donné au lancement du programme, on lance l'interface et on attend la requête de connexion
         */
        else if (args.length < 2) {
            DebugLogger.print(DebugType.COM , "Paramètres de connection non précisés... Attente des informations données à l'interface");
            new ControlGUI(new Frame());
        } 
        
        else {
            // création de l'interface graphique
            Frame frame = new Frame();

            // création du contrôleur de l'interface graphique
            ControlGUI gui = new ControlGUI(frame);

            try {
                // création du client avec les paramètres donnés au programme ainsi que le contrôleur de l'interface graphique
                client = new Client(args[0], Integer.parseInt(args[1]), gui);

                // initialisation des commandes "réseau" pour communication client -> serveur
                Console.connectConsole(client.clientTCP);
                
                // enregistrement du pseudo du joueur
                if (args.length > 2) {
                    if (args[2].length() == 8) {
                        GameInfo.playerID = args[2];
                    }
                }
                else {
                    GameInfo.playerID = "unknUser";
                }

                // désactivation du bouton IG de connexion et activation du bouton IG de déconnexion
                if (isConnected) {
                    frame.getConnectionPanel().getConnectionButton().setEnabled(false);
                    frame.getConnectionPanel().getDisconectionButton().setEnabled(true);
                }
            } catch (NumberFormatException e) {
                DebugLogger.print(DebugType.ERROR, "[Client/ERREUR] : le port donné au lancement du programme ne représente pas une valeur entière");
            }

        }
        
    }

    public static void disconnect() {
        isConnected = false;
        GameInfo.clear(); 
    }

    public ClientTCP getClientTCP() {
        return this.clientTCP;
    }

}