CPP=g++ --std=c++11 -Wall
all : Test.o Horloge.o Date.o
	g++ -Wall -std=c++11 -o go Test.o Horloge.o Date.o
	./go
Horloge.o : Horloge.cpp Horloge.hpp
	g++ -c -Wall -std=c++11 Horloge.cpp
Date.o : Date.cpp Date.hpp
	g++ -c -Wall -std=c++11 Date.cpp
Test.o : Test.cpp Horloge.hpp Date.hpp
	g++ -c -Wall -std=c++11 Test.cpp
clean :
	rm -f *.o go