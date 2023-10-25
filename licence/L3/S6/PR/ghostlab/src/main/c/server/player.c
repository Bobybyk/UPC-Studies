#include "player.h"

struct player* player_create(char *name, int tcp_socket, int port) {
	struct player *p = (struct player *)malloc(sizeof(struct player));

	/* make sure the name is 8 characters long */
	memcpy(p->id,name,8);

	p->score = 0;
	p->ready = 0;
	p->x = 2;
	p->y = 2;
	p->tcp_socket_fd = tcp_socket;
	p->udp_port = port;
	p->ready = FALSE;

	return p;
}	

void player_print(void *player) {
	struct player *p = (struct player *) player;
	if(player == NULL) {
		printf("Empty list...\n");
		return;
	}

	char tmp_name[9];
	strcpy(tmp_name,p->id);
	tmp_name[8] = '\0';

	printf("name: %s score: %d x: %d y: %d port: %d",tmp_name,p->score,p->x,p->y,p->udp_port);
}

int player_is_ready(struct player *p) {
	return p->ready;
}

int player_move(struct client *c, int count, int direction) {
	if(count == 0)
		return 0;
	int **lab = c->game->labyrinth->cells;
	int moved = 0;

	int x = c->player->x;
	int y = c->player->y;

	for(int i=0;i<count;i++) {
		moved++;

		if(direction == UP && (y == 0 || (y-moved) < 0 || lab[y-moved][x] == 1)) {
			moved--;
			break;
		} else if(direction == DOWN && ((y == c->game->labyrinth->height - 1)  || (y+moved == c->game->labyrinth->height) || lab[y+moved][x] == 1)) {
			moved--;
			break;
		} else if(direction == LEFT && ( x == 0 || (x-moved) < 0 || lab[y][x-moved] == 1)) {
			moved--;
			break;
		} else if(direction == RIGHT && ((x == c->game->labyrinth->width - 1) || (x+moved == c->game->labyrinth->width) || lab[y][x+moved] == 1)) {
			moved--;
			break;
		}

		switch(direction) {
			case UP:
				if(game_is_there_ghost(c,x,y-moved)) {
					c->player->score += GHOST_VALUE;
					multicast_score(c,x,y-moved);
				}
				break;
			case DOWN:
				if(game_is_there_ghost(c,x,y+moved)) {
					c->player->score += GHOST_VALUE;
					multicast_score(c,x,y+moved);
				}
				break;
			case LEFT:
				if(game_is_there_ghost(c,x-moved,y)) {
					c->player->score += GHOST_VALUE;
					multicast_score(c,x-moved,y);
				}
				break;
			case RIGHT:
				if(game_is_there_ghost(c,x+moved,y)) {
					c->player->score += GHOST_VALUE;
					multicast_score(c,x+moved,y);
				}
				break;
		}
	}

	switch(direction) {
		case UP:
			c->player->y -= moved;
			break;
		case DOWN:
			c->player->y += moved;
			break;
		case LEFT:
			c->player->x -= moved;
			break;
		case RIGHT:
			c->player->x += moved;
			break;
	}

	return moved;
}

struct player* player_get_by_id(struct game *g, char* id) {
	struct node *cur = *g->players;
	while(1) {

		if(cur->data == NULL)
			break;

		struct player *p = (cur->data);

		char searching[9];
		memset(searching,'\0',9);
		char current[9];
		memset(current,'\0',9);

		memcpy(searching,id,8);
		memcpy(current,p,8);

		if(strcmp(current,searching) == 0)
			return p;

		if(cur->next == NULL)
			break;

		cur = cur->next;
	}
	return NULL;
}

void player_init_pos(struct game *g) {
	struct node *cur = *g->players;

	while(1) {
		if(cur->data == NULL)
			break;

		while(1) {
			int tmp_x = rand() % g->labyrinth->width;
			int tmp_y = rand() % g->labyrinth->height;
			if(g->labyrinth->cells[tmp_y][tmp_x] != 1) {
				((struct player *)cur->data)->x = tmp_x;
				((struct player *)cur->data)->y = tmp_y;
				break;
			}
		}

		if(cur->next == NULL)
			break;
		cur = cur->next;
	}
}
