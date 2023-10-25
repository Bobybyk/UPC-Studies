package client.control.UIBis;

import javax.swing.*;
import java.awt.*;
import java.io.PrintWriter;

import client.view.graphicDisplay.Menu;
import client.ServerInfo;

public class MenuManager {
    
    public MenuManager(Menu view, Thread t) {
        view.getConnect().addActionListener((event) -> writeDatas(view, t));
    }

    private void writeDatas(Menu view, Thread t) {
        ServerInfo.setIp(view.getIp().getText());
        ServerInfo.setPort(view.getPort().getText());
        if (view.getTextName().getText().isEmpty()) {
            ServerInfo.setName("Unnamed_User");
        } else {
            ServerInfo.setName(view.getTextName().getText());
        }
        if (ServerInfo.getIpFormat() && ServerInfo.getPortFormat()) {
            t.start();
        }
    }
}
