#include <ostream>
#include <iostream>
#include <utility>
#include <map>
using namespace std;

class Date {
    private:
        int year, month, day;
        map<int, string> months;
    public:
        Date(int y, int m, int d) : year{y}, month{m}, day{d} {};

        int getYear();
        int getMonth();
        int getDay();
        map<int, string> getMonthsMap();
        void fillMonthsMap();
};

std::ostream& operator << (std::ostream& out, Date &h);