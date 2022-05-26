package main.java.gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.GridLayout;

public class ConnectionPanel extends JPanel {
    private JTextField portField;
    private JTextField ipField;
    private JTextField playerIdField;

    private JButton connectionButton = new JButton("Connect");
    private JButton disconnectButton = new JButton("Disconnect");

    public ConnectionPanel() {
        JLabel portLabel = new JLabel("Port : ");
        JLabel ipLabel = new JLabel("IP : ");
        JLabel playerLabel = new JLabel("player ID :");
    
        portField = new JTextField("12345");
        ipField = new JTextField("localhost");
        playerIdField = new JTextField(8);
    
        connectionButton = new JButton("Connect");
    
        GridLayout connectionLayout = new GridLayout(4, 2);

        this.setLayout(connectionLayout);
        this.add(ipLabel);
        this.add(ipField);
        this.add(portLabel);
        this.add(portField);
        this.add(playerLabel);
        this.add(playerIdField);
        this.add(connectionButton);
        this.add(disconnectButton);

        disconnectButton.setEnabled(false);

    }

    public JTextField getPortField() {
        return portField;
    }
    public JTextField getIpField() {
        return ipField;
    }
    public JTextField getplayerField() {
        return playerIdField;
    }

    public JButton getConnectionButton() {
        return connectionButton;
    }
    public JButton getDisconectionButton() {
        return disconnectButton;
    }
}
