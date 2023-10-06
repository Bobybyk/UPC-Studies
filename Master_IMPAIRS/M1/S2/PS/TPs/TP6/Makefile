CFLAGS=-Wall -Wextra -Werror -pthread
SRC=$(wildcard *.c)
OBJ=$(SRC:.c=.o)
DEP=dyck

.PHONY:	all

all:	clean $(DEP)

clean:
	@rm -f $(DEP)

dyck:
	@$(CC) $(CFLAGS) -o $@ dyck.c