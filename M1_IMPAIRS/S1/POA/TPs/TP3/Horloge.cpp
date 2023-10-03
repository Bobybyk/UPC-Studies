#include <ostream>
#include "Horloge.hpp"
#include <iostream>
using namespace std;

// Surcharge de l ’ op é rateur <<
ostream& operator << (ostream& out , Horloge &x ) {
    out << x.getHours() << ":" << x.getMinutes() << ":" << x.getSeconds() << endl ;
    return out ;
}

int Horloge::getHours() {
    return hours;
}
int Horloge::getMinutes() {
    return minutes;
}
int Horloge::getSeconds() {
    return seconds;
}

int Horloge::tick() {
    seconds+=1;
    if (seconds == 60) {
        minutes+=1;
        seconds = 0;
    }
    if (minutes == 60) {
        hours +=1;
        minutes = 0;
    }
    if (hours == 24) {
        hours = 0;
        return 1;
    }
    return 0;
}