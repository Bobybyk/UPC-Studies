#include "GarbageCollector.hpp"

using namespace std;

Edge::Edge(){};

// Create an edge with Vertices labels
Edge::Edge(string label1, string label2, int weight) : v1{ new Vertex{label1}}, v2{new Vertex{label2}}, weight{weight} {
    GarbageCollector::addEdge(this);
}

// Create an edge with Vertices objetcs
Edge::Edge(Vertex *vertex1, Vertex *vertex2, int weight) : v1{vertex1}, v2{vertex2}, weight{weight} {
    GarbageCollector::addEdge(this);
}

Edge::~Edge() {}

Vertex* Edge::getV1() {
    return v1;
}

Vertex* Edge::getV2() {         //safe getter (returning a pointer to a temporary object)
    return v2;
}

int Edge::getWeight() {
    return weight;
}

std::ostream& operator<<(std::ostream& out, Edge &e) {
    out << "From: " << *e.getV1() << endl << "To: " << *e.getV2() << endl << "With weight: " << e.getWeight();
    return out;
}