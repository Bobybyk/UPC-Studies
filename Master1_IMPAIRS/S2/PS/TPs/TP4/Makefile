CFLAGS=-Wall -Wextra -Werror
SRC=$(wildcard *.c)
OBJ=$(SRC:.c=.o)
DEP=ex1 ex2

.PHONY:	all

all:	clean $(DEP)

clean:
	@rm -f $(DEP)

ex1:
	@$(CC) $(CFLAGS) -o $@ ex1.c

ex2:
	@$(CC) $(CFLAGS) -o $@ ex2.c