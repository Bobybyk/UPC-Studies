#include "GarbageCollector.hpp"

using namespace std;

vector<Edge*> GarbageCollector::edges;
vector<Vertex*> GarbageCollector::vertices;

GarbageCollector::GarbageCollector() {}

void GarbageCollector::cleanup() {

    for(long unsigned int i=0; i<(edges.size()) ; i = i+1) {
        delete edges.at(i);
    }

    for(long unsigned int i=0; i<(vertices.size()) ; i = i+1) {
        delete vertices.at(i);
    }
}

void GarbageCollector::addEdge(Edge *e) {
    edges.push_back(e);
}

void GarbageCollector::addVertex(Vertex *v) {
    vertices.push_back(v);
}

vector<Vertex*> GarbageCollector::getVertices() {
    return vertices;
}

vector<Edge*> GarbageCollector::getEdges() {
    return edges;
}

std::ostream& operator<<(std::ostream& out, GarbageCollector &g) {
    string verticesList = "[";
    string edgesList = "[";

    for (int i = 0 ; i < ((int)GarbageCollector::getVertices().size()) ; i++) {
        if (i == (int)GarbageCollector::getVertices().size()-1) {
            verticesList += GarbageCollector::getVertices()[i]->getLabel() + " (id : " + to_string(GarbageCollector::getVertices()[i]->getId()) + ")]";
        } else {
            verticesList += GarbageCollector::getVertices()[i]->getLabel() + " (id : " + to_string(GarbageCollector::getVertices()[i]->getId()) + "), ";
        }
    }

    for (int i = 0 ; i < ((int)GarbageCollector::getEdges().size()) ; i++) {
        if (i == (int)GarbageCollector::getEdges().size()-1) {
            edgesList += "[" + GarbageCollector::getEdges()[i]->getV1()->getLabel() + ", " + GarbageCollector::getEdges()[i]->getV2()->getLabel() + ", " + to_string(GarbageCollector::getEdges()[i]->getWeight()) + "]]";
        } else {
            edgesList += "[" + GarbageCollector::getEdges()[i]->getV1()->getLabel() + ", " + GarbageCollector::getEdges()[i]->getV2()->getLabel() + ", " + to_string(GarbageCollector::getEdges()[i]->getWeight()) + "], ";
        }
    }

    out << "\nGarbage Collector \nVertices :\n - count : " << GarbageCollector::getVertices().size() << "\n - list : " << verticesList << "\nEdges :\n - count : " << GarbageCollector::getEdges().size() << "\n - list : " << edgesList << "\n";
    return out;
} 