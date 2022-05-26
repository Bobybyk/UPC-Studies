#include "game.h"
#include "labs_parser.h"

int game_id_counter = 0;
int multi_diffusion_port = 1999;

pthread_mutex_t game_list_mutex = PTHREAD_MUTEX_INITIALIZER;

struct game* game_create(int cap) {
	struct game *new_game = malloc(sizeof(struct game));	
	new_game->started = FALSE;
	new_game->id = game_id_counter++;
	new_game->max_capacity = cap;
	new_game->players = llist_create(NULL);
	new_game->labyrinth = parse_lab("assets/lab2.lab");
	debug_lab(new_game->labyrinth); //print the labyrinth

	pthread_mutex_init(&(new_game->game_lock),NULL);

	int ret = inet_aton("225.12.35.42",&new_game->diffusion_ip);
	assert(ret != 0);
	new_game->diffusion_port = multi_diffusion_port++;

	new_game->socket_fd = socket(PF_INET,SOCK_DGRAM,0);

	new_game->remaining_ghosts = MAX_GHOST_NUMBER;
	for(int i=0; i < MAX_GHOST_NUMBER; i++) {
		while(1) {
			int tmp_x = rand() % new_game->labyrinth->width;
			int tmp_y = rand() % new_game->labyrinth->height;
			if(new_game->labyrinth->cells[tmp_y][tmp_x] != 1) {
				new_game->ghosts[i].x = tmp_x;
				new_game->ghosts[i].y = tmp_y;
				break;
			}
		}
		printf("%d %d\n",new_game->ghosts[i].x,new_game->ghosts[i].y); //to display the positions of the ghosts
	}

	extern llist *games;
	llist_push(games,new_game);

	return new_game;
}

void game_delete(struct game *g) {
	llist_free(g->players);
	for (int i = 0; i < g->labyrinth->height; i++) {
		free(g->labyrinth->cells[i]);
	}
	free(g->labyrinth->cells);
	free(g->labyrinth);
	free(g);
}

void game_print(void *game) {
	struct game *g = (struct game *) game;
	if(game == NULL) {
		printf("Empty list...\n");
		return;
	}
	char *ip = inet_ntoa(g->diffusion_ip);
	printf("id: %d max: %d ip: %s port: %d\n",g->id,g->max_capacity,ip,g->diffusion_port);
}

void game_add_player(struct game *g, struct player *p) {
	pthread_mutex_lock(&g->game_lock);

	llist_push(g->players,p);

	pthread_mutex_unlock(&g->game_lock);
}

struct game* game_get_by_id(int id) {

	pthread_mutex_lock(&game_list_mutex);

	struct game *g = NULL;

	extern llist *games;

	struct node *cur = *games;
	while(1) {
		
		if(cur->data == NULL)
			break;	

		if(((struct game*) cur->data)->id == id) {
			g = cur->data;
			break;
		}

		if(cur->next == NULL)
			break;

		cur = cur->next;
	}

	pthread_mutex_unlock(&game_list_mutex);

	return g;
}



int game_are_all_players_ready(struct game *g) {
	pthread_mutex_lock(&(g->game_lock));

	int ready = TRUE;
	struct node *cur = *(g->players);
	if(cur->data == NULL) {
		pthread_mutex_unlock(&(g->game_lock));
		return FALSE;
	}
	while(cur->data != NULL) {
		struct player *p = cur->data;		

		if(p->ready == FALSE) {
			ready = FALSE;
			break;
		}

		if(cur->next == NULL)
			break;
		cur = cur->next;
	}

	pthread_mutex_unlock(&(g->game_lock));
	return ready;
}

void *game_start(void *arg) {

	extern llist *games;

	struct game *g = (struct game*) arg;

	while(1) {
		if(llist_size(g->players) != 0 &&  game_are_all_players_ready(g)) {
			break;		
		}
		usleep(300000);		//wait for 0.3 seconds before checking again
	}
	g->started = TRUE;
	puts("All players are ready");

	pthread_t t;
	pthread_create(&t,NULL,ghosts_move,g);
	player_init_pos(g);
	send_posit(g);

	while(1) {
		if(g->remaining_ghosts == 0)
			break;
		if(llist_size(g->players) == 0)
			return NULL;
		usleep(300000);
	}
	puts("Game over");

	multicast_endga(g);

	return NULL;
}

int game_is_there_ghost(struct client *c, int x, int y) {

	struct game *g = c->game;

	for(int i=0;i<MAX_GHOST_NUMBER;i++) {
		if(g->ghosts[i].x == x && g->ghosts[i].y == y) {


			g->ghosts[i].x = -1;
			g->ghosts[i].y = -1;
			g->remaining_ghosts--;

			return 1;
		}
	}
	return 0;
}

struct player *game_get_winner(struct game *g) {
	struct player *p = NULL;
	int max_points = 0;
	struct node *cur = *(g->players);

	while(1) {
		if(cur->data == NULL)
			break;

		if(((struct player *)cur->data)->score > max_points) {
			max_points = ((struct player *) cur->data)->score;
			p = cur->data;
		}
		
		if(cur->next == NULL)
			break;
		cur = cur->next;
	}

	return p;
}
