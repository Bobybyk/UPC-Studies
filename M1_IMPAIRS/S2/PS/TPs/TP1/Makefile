CFLAGS=-g -Wall -Wextra -O1 -pthread
SRC=$(wildcard *.c)
OBJ= $(SRC:.c=.o)

default: $(OBJ)
clean:
	rm -f $(OBJ)
%.o : %.c
	gcc $(CFLAGS) -o $* $< 
