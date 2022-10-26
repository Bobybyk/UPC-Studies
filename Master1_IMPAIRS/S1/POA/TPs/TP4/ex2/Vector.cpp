#include "Vector.hpp"
#include <ostream>
#include <iostream>
using namespace std;

int Vector::getVectorLength() {
    return length;
}

// Define the constructor.
Vector::Vector(int nbrValues) : length{nbrValues} {
}

// Define the destructor.
Vector::~Vector() {
    cout << "destruction de " << *this << endl;
}

ostream& operator << (ostream& out , Vector &v) {
    out << v.getVectorLength() << " éléments : [" << endl ;
    return out ;
}