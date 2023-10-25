#include "server_socket.h"

void server_socket_init(int socket, int port) {

	/* Initializing the tcp socket for the server *****************************************/
	struct sockaddr_in local_address = {
		.sin_family = AF_INET,
		.sin_port = htons(port),
		.sin_addr.s_addr = htonl(INADDR_ANY)
	};

	int ret = bind(socket,(struct sockaddr *) &local_address,sizeof(local_address));
	if(ret < 0) {
		perror("Bind");
		exit(EXIT_FAILURE);
	}

	ret = listen(socket,0);
	if(ret < 0) {
		perror("Listen");
		exit(EXIT_FAILURE);
	}
	/*************************************************************************************/
}

int server_socket_accept(int socket_fd) {

	struct sockaddr_in remote;
	socklen_t size_remote = sizeof(struct sockaddr_in);
	int fd = accept(socket_fd,(struct sockaddr *) &remote,&size_remote);

	if(fd < 0) {
		perror("Accept");
		exit(EXIT_FAILURE);
	}
	printf("A client has connected: %s\n",inet_ntoa(remote.sin_addr));

	return fd;
}

void *server_socket_before_game_start(void *arg) {

	extern llist *games;
	struct client *c = (struct client *) arg;
	int fd = *(c->fd);

	while(1) {
		char buf[100];	
		int ret = request_read_tcp(buf,fd);
		if(ret < 0) {	//the player disconnected

			llist_remove(c->game->players,c->player);

			free(c->player);
			close(*(c->fd));
			free(c->fd);
			free(c);
			return NULL;
		}

		char cmd[6];
		memcpy(cmd,buf,5);
		cmd[5] = '\0';

		if(strcmp(cmd,"UNREG") == 0) {

			send_unrok(fd,c->game->id);

			llist_remove(c->game->players,c->player);

			int *socket_fd = c->fd;
			free(c->player);
			free(c);

			pthread_t t;
			pthread_create(&t,NULL,server_socket_connection_prompt,socket_fd);

			return NULL;
		} else if(strcmp(cmd,"SIZE?") == 0) {
			uint8_t game_id;
			memcpy(&game_id, buf+6, 1);
			send_size(fd, game_id);
		} else if(strcmp(cmd,"LIST?") == 0) {
			request_list(buf,fd);
		} else if(strcmp(cmd,"GAME?") == 0) {
			send_games(fd,games);
		} else if(strcmp(cmd,"START") == 0) {
			c->player->ready = TRUE;
			break;
		} else {
			send_dunno(fd);
		}
	}

	pthread_t t;
	pthread_create(&t,NULL,server_socket_during_game,c);

	return NULL;
}

void *server_socket_during_game(void *arg) {
	struct client *c = arg;
	int fd = *(c->fd);
	struct game *g = c->game;

	while(1) {
		if(g->started)
			break;
		usleep(300000);		//wait for 0.3 seconds before checking again
	}

	send_welco(fd,g);

	char buf[100];
	while(1) {
		int ret = request_read_tcp(buf,fd);
		if(ret < 0) {
			llist_remove(c->game->players,c->player);

			free(c->player);
			close(*(c->fd));
			free(c->fd);
			free(c);
			return NULL;
		}

		if(g->remaining_ghosts == 0)
			break;

		char cmd[6];
		memcpy(cmd,buf,5);
		cmd[5] = '\0';

		if(strcmp("UPMOV",cmd) == 0) {
			request_movement(buf,c,UP);
		} else if(strcmp("LEMOV",cmd) == 0) {
			request_movement(buf,c,LEFT);
		} else if(strcmp("DOMOV",cmd) == 0) {
			request_movement(buf,c,DOWN);
		} else if(strcmp("RIMOV",cmd) == 0) {
			request_movement(buf,c,RIGHT);
		} else if(strcmp("IQUIT",cmd) == 0) {
			request_iquit(c);			
			break;
		} else if(strcmp("GLIS?",cmd) == 0) {
			request_game_list(c);
		} else if(strcmp("MALL?",cmd) == 0) {
			request_mall(buf,c);
		} else if(strcmp("SEND?",cmd) == 0) {
			request_send(buf,c);
		}

	}

	pthread_t t;
	pthread_create(&t,NULL,server_socket_game_ended,c);
	
	return NULL;
}

void *server_socket_game_ended(void *arg) {
	struct client *c= arg;
	int fd = *((int*) c->fd);

	char buf[100];
	request_read_tcp(buf,fd);

	send(fd,"GOBYE***",8,0);
	close(*(c->fd));
	usleep(GHOST_MOVE_FREQUENCY);

	llist_remove(c->game->players,c->player);

	if(llist_size(c->game->players) == 0) {
		//maybe add usleep(30000000) to wait ffor the ghost moving thread to stop
		extern llist *games;
		llist_remove(games,c->game);
		llist_free(c->game->players);		
		labyrinth_free(c->game->labyrinth);
		free(c->game);
	}

	free(c->player);
	free(c->fd);
	free(c);

	return NULL;
}

void *server_socket_connection_prompt(void *arg) {

	int fd = *((int*) arg);

	extern llist *games;

	struct client *c = server_socket_receive_newpl_regis(fd);	//wait for the client to be in a game
	if( c == NULL)  {	//if c is NULL, then the client disconnected
		free(c);
		return NULL;	
	}
	c->fd = arg;		//update its file descriptor

	pthread_t t;
	pthread_create(&t,NULL,server_socket_before_game_start,c);	//launch the thread handling the next phase

	return NULL;	
}

struct client *server_socket_receive_newpl_regis(int fd) { 		
	extern llist *games;
	struct client *c;

	char buf[100];

	while(1) {		//this function exits once the player sent REGIS or NEWPL
		int ret = request_read_tcp(buf,fd);	//read the client input
		if(ret < 0) {	//the client disconnected
			return NULL;
		}

		char cmd[6];
		memcpy(cmd,buf,5);	//isolate the command
		cmd[5] = '\0';

		if(strcmp(cmd,"NEWPL") == 0) {

			printf("New game creation requested\n");
			c = request_newpl(buf,fd);	//isolate the command arguments then compute and answer
			break;

		} else if(strcmp(cmd,"REGIS") == 0) {

			printf("Game join request\n");
			c = request_regis(buf,fd);
			if(c == NULL)	//if c is NULL then the player couldn't join the game 
				continue;
			break;

		} else if(strcmp(cmd,"GAME?") == 0) {
			send_games(fd,games);
		} else if(strcmp(cmd,"SIZE?") == 0) {
			uint8_t game_id = buf[6];
			send_size(fd, game_id);
		} else if(strcmp(cmd,"LIST?") == 0) {
			request_list(buf,fd);
		} else {
			send_dunno(fd);
		}
	}
	return c;
}
