CPP=g++ --std=c++11 -Wall
all : calculette
calculette : calculette.o
	$(CPP) -o calculette calculette.o
calculette.o : calculette.cpp
	$(CPP) -c calculette.cpp
clean :
	rm *.o