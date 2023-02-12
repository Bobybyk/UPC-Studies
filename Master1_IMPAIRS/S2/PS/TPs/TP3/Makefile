CFLAGS=-Wall -Wextra -Werror
SRC=$(wildcard *.c)
OBJ=$(SRC:.c=.o)
DEP=diff cp ex3

.PHONY:	all

all:	clean $(DEP)

clean:
	@rm -f $(DEP)

diff:
	@$(CC) $(CFLAGS) -o $@ diff.c

cp:
	@$(CC) $(CFLAGS) -o $@ my-cp.c

ex3:
	@$(CC) $(CFLAGS) -o $@ ex3.c