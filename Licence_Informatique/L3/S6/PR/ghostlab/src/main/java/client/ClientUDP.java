package main.java.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Random;

import main.java.commands.CommandUDP;
import main.java.commands.in.udp.CommandRcvUdpPrivateMess;
import main.java.console.Console;
import main.java.console.DebugLogger;
import main.java.console.DebugType;
import main.java.game.GameInfo;
import main.java.gui.controller.ControlGUI;

public class ClientUDP extends Thread {
    private DatagramSocket socket;
    private InetAddress address;
    private ControlGUI gui;

    private HashMap<String,CommandUDP> commandRcvUdpList = new HashMap<String,CommandUDP>();
    
    public static boolean clientUDPCreated = false;

    public ClientUDP(String ip, ControlGUI gui) {
        
        boolean success = false;
        Random r = new Random();
        int port = r.nextInt(9999 - 1000) + 1000;

        int attempt = 1;

        DebugLogger.print(DebugType.COM, "Création du client UDP...");

        while(!success) {
            try {
                
                DebugLogger.print(DebugType.COM, "Tentative numéro " + attempt + "...");
                
                socket = new DatagramSocket(port);
                address = InetAddress.getByName(ip);
                
                DebugLogger.print(DebugType.COM, "...succès");
                GameInfo.portUDP = port;
                
                // remplissage de la liste de commandes recevables
                commandRcvUdpList.put("MESSP", new CommandRcvUdpPrivateMess());
                success = true;

            } catch (SocketException e) {
                DebugLogger.print(DebugType.ERROR, "[ClientUDP/ERREUR] : port UDP déjà utilisé");
                attempt++;
                port = r.nextInt(9999 - 1000) + 1000;
            }
            catch (UnknownHostException e) {
                DebugLogger.print(DebugType.ERROR, "[ClientUDP/ERREUR] : : l'adresse IP de l'hôte ne peut être déterminée");
                break;
            }
        }

        clientUDPCreated = true;
        this.gui = gui;
    }

    @Override
    public void run() {

        while(Client.isConnected) {

            try {

                byte[] buf = new byte[256];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                
                String message = new String(packet.getData(), 0, packet.getLength());
                
                String[] args = message.split(" ");

                // suppression des caractères de fin de ligne "+++"
                args[args.length-1] = args[args.length-1].substring(0, args[args.length-1].length() - 3);
                
                if(commandRcvUdpList.containsKey(args[0])) {
                    commandRcvUdpList.get(args[0]).execute(args);
                }
                else {
                    DebugLogger.print(DebugType.CONFIRM, "Commande inconnue : " + args[0]);
                }
                
            } catch (IOException e) {
                DebugLogger.print(DebugType.ERROR, "[ClientTCP/ERREUR] : erreur lors de la réception d'un message UDP");
                Console.useMessage("killclient");
            }
        }
        clientUDPCreated = false;
        socket.close();
        Client.disconnect();
    }

    public InetAddress getAddr() {
        return address;
    }
}
