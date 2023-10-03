#include "TimeStamp.hpp"
#include <iostream> 
using namespace std;

void TimeStamp::affiche() {
	cout << '(' << heures << ',' << minutes << ',' << secondes << ')' << endl;
}