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
                for (int j = 0 ; j < vertices.size() ; j++) {
                    if (vertices.get(j).getValue() == edges.get(j)[0]) {
                        v1 = vertices.get(j);
                    }
                }
            }
            if (!vertexExist(edges.get(i)[1])) {
                v2 = new Vertex(edges.get(i)[1]);
                vertices.add(v2);
            } else {
                for (int j = 0 ; j < vertices.size() ; j++) {
                    if (vertices.get(j).getValue() == edges.get(j)[1]) {
                        v2 = vertices.get(j);
                    }
                }
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

    private boolean vertexExist(int value) {
        for (int i = 0 ; i < vertices.size() ; i++) {
            if (vertices.get(i).getValue() == value) {
                return true;
            }
        }
        return false;
    }
}