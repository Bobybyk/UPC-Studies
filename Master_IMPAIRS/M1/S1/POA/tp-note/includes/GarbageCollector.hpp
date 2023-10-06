#ifndef _GARBAGECOLLECTOR
#define _GARBAGECOLLECTOR

#include "Vertex.hpp"
#include "Edge.hpp"
#include <iostream>
#include <vector>

class GarbageCollector {
    public:
        GarbageCollector();

        static void cleanup();

        static void addEdge(Edge *e);
        static void addVertex(Vertex *v);

        static std::vector<Vertex*> getVertices();
        static std::vector<Edge*> getEdges(); 

    private:
        static std::vector<Vertex*> vertices;
        static std::vector<Edge*> edges;
};

std::ostream& operator<<(std::ostream& out, GarbageCollector &g);

#endif