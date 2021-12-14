package client.view.graphicDisplay;

import javax.swing.*;
import java.awt.*;

import client.ServerInfo;

public class Menu extends JFrame {
    private JLabel labelName = new JLabel("enter your name : ");
    private JLabel labelIp = new JLabel("Enter server ip : ");
    private JLabel labelPort = new JLabel("Enter server port : ");
    private JLabel labelPassword = new JLabel("Enter server pass (optional) : ");
    private JTextField textName = new JTextField(15);
    private JTextField textIp = new JTextField(20);
    private JTextField textPort = new JTextField(5);
    private JPasswordField fieldPassword = new JPasswordField(20);
    private JButton buttonLogin = new JButton("connect");
     
    public Menu() {
        super("Menu");
         
        // create a new panel with GridBagLayout manager
        JPanel newPanel = new JPanel(new GridBagLayout());
         
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);
         
        // add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;     
        newPanel.add(labelName, constraints);

        constraints.gridx = 1;
        newPanel.add(textName, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;     
        newPanel.add(labelIp, constraints);
 
        constraints.gridx = 1;
        newPanel.add(textIp, constraints);
         
        constraints.gridx = 0;
        constraints.gridy = 2;     
        newPanel.add(labelPort, constraints);
         
        constraints.gridx = 1;
        newPanel.add(textPort, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;     
        newPanel.add(labelPassword, constraints);
         
        constraints.gridx = 1;
        newPanel.add(fieldPassword, constraints);
         
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        newPanel.add(buttonLogin, constraints);
         
        // set border for the panel
        newPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Login Panel"));
         
        // add the panel to this frame
        add(newPanel);
         
        pack();
        setLocationRelativeTo(null);  
        
    }

    public JTextField getTextName() {
        return this.textName;
    }
    public JTextField getIp() {
        return this.textIp;
    }
    public JTextField getPort() {
        return this.textPort;
    }
    public JButton getConnect() {
        return this.buttonLogin;
    }
}
