#include "GarbageCollector.hpp"

using namespace std;

// Create a vertex without args
Vertex::Vertex() : label{"default"}, id{-1} {
    GarbageCollector::addVertex(this);
}
// Create a vertex with a label
Vertex::Vertex(string label) : label{label}, id{-1} {
    GarbageCollector::addVertex(this);
}

Vertex::~Vertex() {}

string Vertex::getLabel() {
    return label;
}
int Vertex::getId() {
    return id;
}

void Vertex::setId(int id) {
    this->id = id;
}

std::ostream& operator<<(std::ostream& out, Vertex &v) {
    out << v.getLabel() << " (id : " << v.getId() << ")";
    return out;
}