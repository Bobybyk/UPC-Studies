CPP=g++ --std=c++11 -Wall
all : Test.o BoxInt.o
	g++ -Wall -std=c++11 -o go Test.o BoxInt.o
	./go
BoxInt.o : BoxInt.cpp BoxInt.hpp
	g++ -c -Wall -std=c++11 BoxInt.cpp
Test.o : Test.cpp BoxInt.hpp
	g++ -c -Wall -std=c++11 Test.cpp
clean :
	rm -f *.o go