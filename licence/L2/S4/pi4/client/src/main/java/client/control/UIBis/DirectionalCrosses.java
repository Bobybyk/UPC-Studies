package client.control.UIBis;

import javax.swing.*;
import java.awt.*;
import java.io.PrintWriter;

import client.view.graphicDisplay.ControlDisplay;
import client.connex.Communication;
import client.commands.Command;
import client.commands.userIn.CommandMove;

public class DirectionalCrosses {

    public DirectionalCrosses(ControlDisplay view, PrintWriter out) {
        CommandMove move = new CommandMove(out);
        String[] cmdUp = {"200", "UP"};
        String[] cmdDown = {"200", "DOWN"};
        String[] cmdLeft = {"200", "LEFT"};
        String[] cmdRight = {"200", "RIGHT"};
        view.getUp().addActionListener((event) -> move.execute(null, cmdUp));
        view.getDown().addActionListener((event) -> move.execute(null, cmdDown));
        view.getLeft().addActionListener((event) -> move.execute(null, cmdLeft));
        view.getRight().addActionListener((event) -> move.execute(null, cmdRight));

    }
}