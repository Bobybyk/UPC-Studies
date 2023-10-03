#include <ostream>
#include "BoxInt.hpp"
#include <iostream>
using namespace std;


// Define the constructor.
BoxInt::BoxInt(int val) : valeur{val} {
    cout << "creation de " << *this << endl;
    nbrInstances+=1;
}

// Define the destructor.
BoxInt::~BoxInt() {
    cout << "destruction de " << *this << endl;
    nbrInstances-=1;
}

ostream& operator << (ostream& out , BoxInt &val) {
    out << "instance num " << val.alive_count() << " : " << val.getInt() << endl ;
    return out ;
}

int BoxInt::nbrInstances = 0;

int BoxInt::getInt() {
    return valeur;
}
int BoxInt::alive_count() {
    return nbrInstances;
}
void BoxInt::setInt(int valeur) {
    this->valeur = valeur;
}