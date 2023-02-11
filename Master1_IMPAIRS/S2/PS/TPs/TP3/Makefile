CFLAGS=-Wall -Wextra -Werror
SRC=$(wildcard *.c)
OBJ=$(SRC:.c=.o)
DEP=diff

.PHONY:	all

all:	clean $(DEP)

clean:
	@rm -f $(DEP)

diff:
	@$(CC) $(CFLAGS) -o $@ diff.c

cp:
	@$(CC) $(CFLAGS) -o $@ my-cp.c