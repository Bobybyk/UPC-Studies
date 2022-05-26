#ifndef MULTICAST_H
#define MULTICAST_H

#include "game.h"
#include "client.h"
#include "dependencies.h"
#include "format.h"

void multicast_send(char *buf, int len, struct game *g);
void multicast_messa(char *messa, struct client *c);
void multicast_ghost(struct game *g, int x, int y);
void multicast_score(struct client *c, int x, int y);
void multicast_endga(struct game *g);

#endif
