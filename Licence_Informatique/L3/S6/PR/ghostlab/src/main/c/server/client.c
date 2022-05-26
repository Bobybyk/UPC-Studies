#include "client.h"

struct client *create_client(struct game *g, struct player *p, int *fd) {
	struct client *c = (struct client*) malloc(sizeof(struct client));

	c->game = g;
	c->player = p;
	c->fd = fd;

	return c;
}
