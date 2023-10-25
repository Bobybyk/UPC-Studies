package model;

import java.util.LinkedList;

public class Vertex {
    private int value;
    private LinkedList<Vertex> adjacents;
    
    public Vertex(int value) {
        this.value = value;
        adjacents = new LinkedList<Vertex>();
    }

    public int getValue() {
        return value;
    }
    public LinkedList<Vertex> getAdjacents() {
        return adjacents;
    }

    public void addAdjacent(Vertex v) {
        adjacents.add(v);
    }
}
