package main.java.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.HashMap;

import main.java.commands.CommandUDP;
import main.java.commands.in.udp_multicast.CommandRcvMultUdpEndGame;
import main.java.commands.in.udp_multicast.CommandRcvMultUdpGhostPos;
import main.java.commands.in.udp_multicast.CommandRcvMultUdpMessage;
import main.java.commands.in.udp_multicast.CommandRcvMultUdpScore;
import main.java.console.Console;
import main.java.console.DebugLogger;
import main.java.console.DebugType;
import main.java.game.GameInfo;
import main.java.gui.controller.ControlGUI;

public class Multicast extends Thread {
    private MulticastSocket multSocket;
    private ControlGUI gui;

    private HashMap<String,CommandUDP> commandRcvMulticastUdpList = new HashMap<String,CommandUDP>();
    
    public Multicast(ControlGUI gui) {
        try {
            multSocket = new MulticastSocket(GameInfo.portMulticast);
            multSocket.joinGroup(InetAddress.getByName(GameInfo.ipMulticast));

            // remplissage de la liste de commandes recevables
            commandRcvMulticastUdpList.put("GHOST", new CommandRcvMultUdpGhostPos());
            commandRcvMulticastUdpList.put("ENDGA", new CommandRcvMultUdpEndGame());
            commandRcvMulticastUdpList.put("MESSA", new CommandRcvMultUdpMessage());
            commandRcvMulticastUdpList.put("SCORE", new CommandRcvMultUdpScore());

            this.gui = gui;
        } catch (IOException e) {
            DebugLogger.print(DebugType.ERROR, "[ClientUDP/ERREUR] : erreur lors de la connexion au multicast");
            System.out.println("");
            Console.useMessage("killclient");
        }
    }

    @Override
    public void run() {

        while (Client.isConnected) {
            byte[] buf = new byte[256];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            
            try {
                multSocket.receive(packet);
                String message = new String(packet.getData(), 0, packet.getLength());
                
                String[] args = message.split(" ");

                // suppression des caractères de fin de ligne "+++"
                args[args.length-1] = args[args.length-1].substring(0, args[args.length-1].length() - 3);
                
                if(commandRcvMulticastUdpList.containsKey(args[0])) {
                    commandRcvMulticastUdpList.get(args[0]).execute(args);
                }
                gui.actualise();
            } catch (IOException e) {
                DebugLogger.print(DebugType.ERROR, "[Multicast/ERREUR] : erreur lors de la réception d'un packet multicast");
                Console.useMessage("killclient");
            }
        }
        multSocket.close();
        Client.disconnect();
    }
}
