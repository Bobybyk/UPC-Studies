CFLAGS=-Wall -Wextra -Werror
SRC=$(wildcard *.c)
OBJ=$(SRC:.c=.o)
DEP=twice repeat si devinette exo6

.PHONY:	all

all:	clean $(DEP)

clean:
	@rm -f $(DEP)

twice:
	@$(CC) $(CFLAGS) -o $@ exo3.c
repeat:
	@$(CC) $(CFLAGS) -o $@ exo3.c
si:
	@$(CC) $(CFLAGS) -o $@ exo4.c
devinette:
	@$(CC) $(CFLAGS) -o $@ exo5.c
exo6:
	@$(CC) $(CFLAGS) -o $@ exo6.c