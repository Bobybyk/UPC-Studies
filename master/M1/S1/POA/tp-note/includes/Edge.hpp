#ifndef _EDGE
#define _EDGE

#include "Vertex.hpp"
#include <iostream>

class Edge {
    public:
        Edge();
        Edge(std::string label1, std::string label2, int weight);
        Edge(Vertex *v1, Vertex *v2, int weight);
        ~Edge();

        Vertex *getV1();
        Vertex *getV2();
        int getWeight();
        
    private:
        Vertex *v1;
        Vertex *v2;
        int weight;
};

std::ostream& operator<<(std::ostream& out, Edge &e);

#endif