#ifndef PLAYER_H
#define PLAYER_H

#include "dependencies.h"
#include "client.h"
#include "game.h"
#include "requests.h"

struct player {
	char id[8];
	int score;
	int x;
	int y;
	int tcp_socket_fd;
	int udp_port;
	int ready;
};

struct player* player_create(char*,int,int); 	//create a player 

//the function to use with llist to display the list of players
void player_print(void *player);

//returns the state of the player
int player_is_ready(struct player *p);

//moves the player inside the labyrinth
int player_move(struct client *c, int count, int direction);

struct player* player_get_by_id(struct game *g, char* id);
void player_init_pos(struct game *g);

#endif
