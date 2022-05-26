CC=gcc
CFLAGS=-Wall -Wextra -g -pthread -Iinclude/
SRC=$(wildcard *.c)
OBJ=$(SRC:.c=.o)
SRC2=$(wildcard build/*.o)

default: $(OBJ) link

link:
	$(CC) $(CFLAGS) -o server $(SRC2)

%.o : %.c
	$(CC) $(CFLAGS) -c -o build/$@ $<

clean:
	@rm -rf build/*.o server
