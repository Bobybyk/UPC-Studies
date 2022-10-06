#include "Horloge.hpp"
#include "Date.hpp"
#include <iostream>

int main() {
    int h, m, s;
    while(true) {
        std::cout<<"Heures : ";
        std::cin>>h;
        if (h >= 24 || h < 0) {
            std::cout<<"Donner une heure comprise entre 0 et 23 inclus\n" << std::endl;
        } else { break; }
    }
    while(true) {
        std::cout<<"Minutes : ";
        std::cin>>m;
        if (m >= 60 || m < 0) {
            std::cout<<"Donner un nombre de minutes compris entre 0 et 59 inclus\n" << std::endl;
        } else { break; }
    }
    while(true) {
        std::cout<<"Secondes : ";
        std::cin>>s;
        if (s >= 60 || s < 0) {
            std::cout<<"Donner un nombre de secondes compris entre 0 et 59 inclus\n" << std::endl;
        } else { break; }
    }

    Horloge horloge{h, m, s};
    std::cout << horloge << std::endl;
    horloge.tick();
    std::cout << horloge << std::endl;

    Date date{11, 06, 1999};
    std::cout << date << std::endl;
    
}