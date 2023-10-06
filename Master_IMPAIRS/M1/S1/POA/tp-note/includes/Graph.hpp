#ifndef _GRAPH
#define _GRAPH

#include "Vertex.hpp"
#include "Edge.hpp"
#include <iostream>
#include <string>
#include <vector>

class Graph {
    public:
        Graph();

        void addVertex(Vertex *v);
        void addVertex(std::string tag);

        void addEdge(Edge *e);
        void addEdge(Vertex *v1, Vertex *v2, int weight);
        void addEdge(std::string label1, std::string label2, int weight);


        int getWeight();
        std::vector<Vertex*> getVertices();
        std::vector<Edge*> getEdges();

        void symmetrize();

        Graph* kruskal();

        static int vertexIdCount;

    private:
        std::vector<Edge*> edges;
        std::vector<Vertex*> vertices;

        void createSet(Vertex *v);
        void sortEdges();
        void vertexUnion(Vertex *u, Vertex *v);
        int find (Vertex u);
};

std::ostream& operator << (std::ostream& out, Graph &g);

#endif