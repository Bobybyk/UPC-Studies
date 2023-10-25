#ifndef GAME_H
#define GAME_H

#include "dependencies.h"
#include "llist.h"
#include "player.h"
#include "labs_parser.h"
#include "ghosts.h"
#include "server_socket.h"

#define MAX_GHOST_NUMBER 5

struct ghost {
	int x;
	int y;
};

struct game {
	int id;
	int max_capacity;
	int started;
	struct in_addr diffusion_ip;
	u_int16_t diffusion_port;
	int socket_fd;
	llist *players;
	pthread_mutex_t game_lock;
	labyrinth *labyrinth;
	struct ghost ghosts[MAX_GHOST_NUMBER];
	int remaining_ghosts;
};

struct game* game_create(int);
void game_print(void *game);
void game_add_player(struct game *g, struct player *p);
struct game* game_get_by_id(int id);
void *game_start(void *arg);
int game_is_there_ghost(struct client *c,int x, int y);
struct player *game_get_winner(struct game *g);
void game_delete(struct game *g);

#endif
