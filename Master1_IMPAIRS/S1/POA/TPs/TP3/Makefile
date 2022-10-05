CPP=g++ --std=c++11 -Wall
all : Test.o Horloge.o
	g++ -Wall -std=c++11 -o go Test.o Horloge.o
	./go
Horloge.o : Horloge.cpp Horloge.hpp
	g++ -c -Wall -std=c++11 Horloge.cpp
Test.o : Test.cpp Horloge.hpp
	g++ -c -Wall -std=c++11 Test.cpp
clean :
	rm -f *.o go