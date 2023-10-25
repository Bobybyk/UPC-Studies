#include <stdio.h>
#include <stdlib.h>
#include <ctime>
#include <iostream>

int main() {
	srand((unsigned) time(0));
	int a { 1 + (rand() % 100) };
	int b { 1 + (rand() % 100) };
	int c;
	int sum = a + b;
	printf("a : %d\n", a);
	printf("b : %d\n", b);
	std::cout<<"Que vaut a+b? ";

	while (true) {
		std::cin>>c;
		if (c == sum) {
			std::cout<<"Bravo !\n" << std::endl;
			break;
		} else {
			std::cout << "Faux !" << std::endl;
			std::cout<<"Que vaut a+b? ";
		}
		std::cout<<"\n";
	}
	return 0;
}

