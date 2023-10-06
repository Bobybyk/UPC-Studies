package controller;

import model.Graph;
import model.Parser;
import view.Window;

public class Control {
    private Window view;

    public Control(Window view) {
        this.view = view;

        Parser.parseGR();
        Graph g = new Graph();

        this.view.drawGraph(g);
    }
}
