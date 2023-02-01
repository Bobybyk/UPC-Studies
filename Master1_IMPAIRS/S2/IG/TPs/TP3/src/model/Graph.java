package model;

import java.util.LinkedList;

public class Graph {
    private LinkedList<Vertex> vertices;

    public Graph() {
        vertices = new LinkedList<Vertex>();
        LinkedList<Integer[]> edges = Parser.getEdgesGR();
        for (int i = 0 ; i < edges.size() ; i++) {
            Vertex v1 = new Vertex(-1);
            Vertex v2 = new Vertex(-1);
            if (!vertexExist(edges.get(i)[0])) {
                v1 = new Vertex(edges.get(i)[0]);
                vertices.add(v1);
            } else {
                v1 = getVertexById(edges.get(i)[0]);
            }
            if (!vertexExist(edges.get(i)[1])) {
                v2 = new Vertex(edges.get(i)[1]);
            } else {
                v2 = getVertexById(edges.get(i)[1]);
            }
            v1.addAdjacent(v2);
        }

        for (int i = 0 ; i < vertices.size() ; i++) {
            System.out.print(vertices.get(i).getValue() + " : ");
            for (int j = 0 ; j < vertices.get(i).getAdjacents().size() ; j++) {
                System.out.print(vertices.get(i).getAdjacents().get(j).getValue() + " ");
            }
            System.out.println();
        }
    }

    private Vertex getVertexById(int id) {
        for (int i = 0 ; i < vertices.size() ; i++) {
            if (vertices.get(i).getValue() == id) {
                return vertices.get(i);
            }
        }
        return null;
    }

    private boolean vertexExist(int value) {
        for (int i = 0 ; i < vertices.size() ; i++) {
            if (vertices.get(i).getValue() == value) {
                return true;
            }
        }
        return false;
    }

    public LinkedList<Vertex> getVertices() {
        return vertices;
    }
}