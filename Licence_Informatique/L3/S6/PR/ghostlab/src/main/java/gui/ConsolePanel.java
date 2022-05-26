package main.java.gui;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ConsolePanel extends JPanel {

    private static JTextArea textArea = new JTextArea(24, 80);

    public ConsolePanel(int width, int height) {
        setSize(width, height);
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.LIGHT_GRAY);
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        textArea.setEditable(false);

        this.add(new JScrollPane(textArea));
        
        printLaunchMessage();
    }

    public static void printLaunchMessage() {
        textArea.append("################## INTERFACE FEED-BACK GHOSTLAB ##################\n");
        textArea.append("#                                                                #\n");
        textArea.append("#      ATTENTION : ceci n'est pas un terminal de commandes !     #\n");
        textArea.append("#                                                                #\n");
        textArea.append("#      N.B : Seuls sont affichés ici les messages de retour      #\n");
        textArea.append("#            de Ghostlab (debog, messages serveur...)            #\n");
        textArea.append("#                                                                #\n");
        textArea.append("#                          --------------                        #\n");
        textArea.append("#                                                                #\n");
        textArea.append("#            Pour plus d'informations, voir README.md            #\n");
        textArea.append("#           tapez la commande help dans votre terminal           #\n");
        textArea.append("#         ou utilisez le bouton d'aide en haut à gauche          #\n");
        textArea.append("#                                                                #\n");
        textArea.append("##################################################################\n");
        textArea.append("\n");
    }

    public static void print(String str) {
        textArea.append(str);
        textArea.setCaretPosition(textArea.getText().length());
    }
    
}
