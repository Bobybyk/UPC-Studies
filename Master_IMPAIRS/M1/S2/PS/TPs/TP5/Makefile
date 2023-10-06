CFLAGS=-Wall -Wextra -Werror
SRC=$(wildcard *.c)
OBJ=$(SRC:.c=.o)
DEP=eratosthene

.PHONY:	all

all:	clean $(DEP)

clean:
	@rm -f $(DEP)

eratosthene:
	@$(CC) $(CFLAGS) -o $@ eratosthene.c