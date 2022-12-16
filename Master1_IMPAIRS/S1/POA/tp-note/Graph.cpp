#include "Graph.hpp"

#include<algorithm>

using namespace std;

int Graph::vertexIdCount = 0;

Graph::Graph() {}

// add a vertex to the graph and check that it hasn't been already added
void Graph::addVertex(Vertex *v) {
    bool isVertexPresent = false;
    for (int i = 0 ; i < ((int)vertices.size()) ; i++) {
        if (vertices[i] == v) {
            isVertexPresent = true;
        }
    }
    if (isVertexPresent) {
        vertices.push_back(v);
    }
}

// add a vertex to the graph with a label to create it before
void Graph::addVertex(std::string tag) {
    Vertex *v = new Vertex(tag);
    vertices.push_back(v);
}

// add an edge to the graph and check that it hasn't been already added
void Graph::addEdge(Edge *e) {
    edges.push_back(e);
    bool isV1Present = false;
    bool isV2Present = false;
    for (int i = 0 ; i < ((int)vertices.size()) ; i++) {
        if (e->getV1() == vertices[i]) {
            isV1Present = true;
        }
        if (e->getV2() == vertices[i]) {
            isV2Present = true;
        }
    }
    if (!isV1Present) {
        vertices.push_back(e->getV1());
    } 
    if (!isV2Present) {
        vertices.push_back(e->getV2());
    }
}

// add an edge to the graph by creating it before
void Graph::addEdge(Vertex *v1, Vertex *v2, int weight) {
    Edge *e = new Edge(v1, v2, weight);
    vertices.push_back(v1);
    vertices.push_back(v2);
    edges.push_back(e);
}

// add an edge to the graph by creating it and its Vertices before
void Graph::addEdge(std::string label1, std::string label2, int weight) {
    Vertex *v1 = new Vertex(label1);
    Vertex *v2 = new Vertex(label2);
    Edge *e = new Edge(v1, v2, weight);
    vertices.push_back(v1);
    vertices.push_back(v2);
    edges.push_back(e);
}

int Graph::getWeight() {
    int w = 0;
    for (int i = 0 ; i < ((int) edges.size()) ; i++) {
        w += edges[i]->getWeight();
    }
    return w;
}

vector<Vertex*> Graph::getVertices() {
    return vertices;
}
vector<Edge*> Graph::getEdges() {
    return edges;
}

void Graph::symmetrize() {

    vector<Edge*> symmetrizedGraph;
    int checkDuplicate = 0;

    for (int i = 0 ; i < ((int)edges.size()) ; i++) {
        for (int j = 0 ; j < ((int)edges.size()) ; j++) {
            if (edges[i]->getV1() == edges[j]->getV2() && edges[i]->getV2() == edges[j]->getV1()) {
                checkDuplicate++;
            }
        }
        if (checkDuplicate == 0) {
            Edge *e = new Edge(edges[i]->getV2(), edges[i]->getV1(), edges[i]->getWeight());
            symmetrizedGraph.push_back(e);
        } else {
            checkDuplicate = 0;
        }
    }

    for (int i = 0 ; i < ((int)symmetrizedGraph.size()) ; i++) {
        edges.push_back(symmetrizedGraph[i]);
    }
    
}

void Graph::createSet(Vertex *v) {
    v->setId(vertexIdCount); 
    vertexIdCount++;
}

void Graph::sortEdges() {
    for (int i = 0 ; i < ((int) edges.size()) ; i++) {
        for (int j = i+1 ; j < ((int) edges.size()) ; j++) {
            if (edges[j]->getWeight() < edges[i]->getWeight()) {
                std::swap(edges[i],edges[j]);
            }
        }
    }
}

void Graph::vertexUnion(Vertex *u, Vertex *v) {
    u->setId(v->getId());
}

int Graph::find(Vertex u) {
    return u.getId();
}

Graph* Graph::kruskal() {

    Graph *tree = new Graph();
     
    for (int i = 0 ; i < ((int)vertices.size()) ; i++) {
        createSet(vertices[i]);
    }

    sortEdges();

    for(int i = 0; i< ((int)edges.size()); i++) {
        if(find(*edges[i]->getV1()) != find(*edges[i]->getV2())) {
            tree->addEdge(edges[i]);
            vertexUnion(edges[i]->getV1(),edges[i]->getV2());
        }
    }

    return tree;
}

std::ostream& operator<<(std::ostream& out, Graph &g) {
    string verticesList = "[";
    string edgesList = "[";

    for (int i = 0 ; i < ((int)g.getVertices().size()) ; i++) {
        if (i == (int)g.getVertices().size()-1) {
            verticesList += g.getVertices()[i]->getLabel() + " (id : " + to_string(g.getVertices()[i]->getId()) + ")]";
        } else {
            verticesList += g.getVertices()[i]->getLabel() + " (id : " + to_string(g.getVertices()[i]->getId()) + "), ";
        }
    }

    for (int i = 0 ; i < ((int)g.getEdges().size()) ; i++) {
        if (i == (int)g.getEdges().size()-1) {
            edgesList += "[" + g.getEdges()[i]->getV1()->getLabel() + ", " + g.getEdges()[i]->getV2()->getLabel() + ", " + to_string(g.getEdges()[i]->getWeight()) + "]]";
        } else {
            edgesList += "[" + g.getEdges()[i]->getV1()->getLabel() + ", " + g.getEdges()[i]->getV2()->getLabel() + ", " + to_string(g.getEdges()[i]->getWeight()) + "], ";
        }
    }

    out << "Vertices :\n - count : " << g.getVertices().size() << "\n - list : " << verticesList << "\nEdges :\n - count : " << g.getEdges().size() << "\n - list : " << edgesList << "\n";
    return out;
} 