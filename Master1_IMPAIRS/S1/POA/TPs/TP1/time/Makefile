CPP=g++ --std=c++11 -Wall
all : Test.o TimeStamp.o
	g++ -Wall -std=c++11 -o go Test.o TimeStamp.o
	./go
TimeStamp.o : TimeStamp.cpp TimeStamp.hpp
	g++ -c -Wall -std=c++11 TimeStamp.cpp
Test.o : Test.cpp TimeStamp.hpp
	g++ -c -Wall -std=c++11 Test.cpp
clean :
	rm -f *.o go