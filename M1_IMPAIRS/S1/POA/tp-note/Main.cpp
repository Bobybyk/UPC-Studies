#include "GarbageCollector.hpp"
#include "Vertex.hpp"
#include "Edge.hpp"
#include "Graph.hpp"
#include <iostream>

using namespace std;

int main() {

    //Création du graphe 
    Graph *g = new Graph();

    //Création des sommets
    Vertex *v1 = new Vertex("v1");
    Vertex *v2 = new Vertex("v2");
    Vertex *v3 = new Vertex("v3");
    Vertex *v4 = new Vertex("v4");
    Vertex *v7 = new Vertex("v7");
    Vertex *v8 = new Vertex("v8");
    Vertex *v9 = new Vertex("v9");
    Vertex *v10 = new Vertex("v10");

    //Création des arêtes 
    Edge *e = new Edge(v1,v2,12);           //Utilisation du premier constructeur    
    Edge *e2 = new Edge(v3,v4,15);
    Edge *e3 = new Edge("v5","v6",40);      //Utilisation du deuxième constructeur
    Edge *e4 = new Edge(v7,v8,1);
    Edge *e5 = new Edge(v9,v10,22);
    Edge *e6 = new Edge(v2, v10, 100);
    Edge *e7 = new Edge(v2,v9,1);
    Edge *e8 = new Edge(v1,v10,100);
    Edge *e9 = new Edge(v2,v3,40);
    Edge *e10 = new Edge(v4,e3->getV1(),68);    //Réutilisation d'un sommet créé avec un label
    Edge *e11 = new Edge(e3->getV2(),v7,39);   

    //Ajout de toutes les arêtes au graphe 
    g->addEdge(e);
    g->addEdge(e2);
    g->addEdge(e3);
    g->addEdge(e4);
    g->addEdge(e5);
    g->addEdge(e6);
    g->addEdge(e7);
    g->addEdge(e8);
    g->addEdge(e9);
    g->addEdge(e10);
    g->addEdge(e11);

    //Afficahge du graphe initial
    cout << "Graphe initial:" << endl << *g << endl;
    cout << "Poids du graphe intial: " << g->getWeight() << endl << endl;

    //Symétrisation du graphe initial
    g->symmetrize();

    //Appliation de l'algorithme de Kruskal
    Graph *tree = g->kruskal();

    //Affichage de l'arbre couvrant de poids minimal
    cout << "Arbre couvrant de poids minimal: " << endl << *(tree) << endl;
    cout << "Poids de l'arbre: " << tree->getWeight() << endl;

    //Suppression du graphe initial ainsi que de l'arbre issu de Kruskal
    delete g;
    delete tree;

    //Appel au garbage collector afin de libérer toute la mémoire ayant été allouée 
    GarbageCollector::cleanup();
}