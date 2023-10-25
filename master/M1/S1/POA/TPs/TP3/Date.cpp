#include <ostream>
#include "Date.hpp"
#include <iostream>
#include <map>
using namespace std;

// Surcharge de l ’ op é rateur <<
ostream& operator << (ostream& out , Date &x ) {
    out << x.getDay() << " " << x.getMonthsMap().find(x.getMonth())->second << " " << x.getYear() << endl ;
    return out ;
}

int Date::getDay() {
    return day;
}
int Date::getMonth() {
    return month;
}
int Date::getYear() {
    return year;
}

void Date::fillMonthsMap() {
    months.insert(pair<int, string>(1, "janvier"));
    months.insert(pair<int, string>(2, "février"));
    months.insert(pair<int, string>(3, "mars"));
    months.insert(pair<int, string>(4, "avril"));
    months.insert(pair<int, string>(5, "mais"));
    months.insert(pair<int, string>(6, "juin"));
    months.insert(pair<int, string>(7, "juillet"));
    months.insert(pair<int, string>(8, "août"));
    months.insert(pair<int, string>(9, "septembre"));
    months.insert(pair<int, string>(10, "octobre"));
    months.insert(pair<int, string>(11, "novembre"));
    months.insert(pair<int, string>(12, "décembre"));
}