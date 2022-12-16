CC=g++
CFLAGS=-Iincludes/ -Wall
SRC= $(wildcard *.cpp)
OBJ= $(SRC:.cpp=.o)
NAME=tp6

all: $(NAME)

$(NAME): $(OBJ)
	$(CC) $(CFLAGS) $^ -o $@

%.o: %.cpp
	$(CC) $(CFLAGS) -o $@ -c $<

clean:
	rm -rf *.o tp6