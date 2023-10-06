#include "BoxInt.hpp"
#include <iostream>

void fonction1 (BoxInt t) {
    t.setInt(36) ;
}
void fonction2 (BoxInt *t) {
    t->setInt(666);
}
void fonction3 (BoxInt &t) {
    t.setInt(1);
}

// pas possible car argument const
/* void fonction4 (const BoxInt &t) {
    t.setInt(13) ;
} */
/* void fonction5 (const BoxInt *t) {
    t->setInt(13) ;
} */

void un_test ( ) {
    BoxInt un_int {42};
    std::cout << un_int ;
    BoxInt un_autre_int {un_int } ;
    std::cout << un_autre_int ;
}


int main() {
    BoxInt monTest {42};
    std::cout << monTest ;
    monTest.setInt(0) ;
    std::cout << monTest ;
    fonction1 ( monTest ) ;
    std::cout << monTest ;
    fonction2 (&monTest ) ;
    std::cout << monTest ;
    fonction3 ( monTest ) ;
    std::cout << monTest ;
    un_test();
}