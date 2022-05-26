#include "multicast.h"

void multicast_send(char *buf, int len, struct game *g) {
	struct sockaddr_in addr = {
		.sin_family = PF_INET,
		.sin_port = htons(g->diffusion_port),
		.sin_addr = g->diffusion_ip
	};
	int ret = sendto(g->socket_fd,buf,len,0,(struct sockaddr *) &addr,(socklen_t) sizeof(struct sockaddr_in));
	if(ret < 0) {
		perror("send");
		exit(1);
	}
}

void multicast_messa(char *messa, struct client *c) {
	char buf[300];
	memcpy(buf,"MESSA ",6);
	memcpy(buf+6,c->player->id,8);
	memcpy(buf+14," ",1);
	memcpy(buf+15,messa,strlen(messa));
	memcpy(buf+15+strlen(messa),"+++",3);
	multicast_send(buf,18+strlen(messa),c->game);
}

void multicast_ghost(struct game *g, int x, int y) {

	char buf[100];

	char *xchar = format_3digits(x);
	char *ychar = format_3digits(y);

	memcpy(buf,"GHOST ",6);
	memcpy(buf+6,ychar,3);
	memcpy(buf+9," ",1);
	memcpy(buf+10,xchar,3);
	memcpy(buf+13,"+++",3);
	multicast_send(buf,16,g);

	free(xchar);
	free(ychar);
}

void multicast_score(struct client *c, int x, int y) {

	char buf[100];

	char *xchar = format_3digits(x);
	char *ychar = format_3digits(y);
	char *score = format_4digits(c->player->score);

	memcpy(buf,"SCORE ",6);
	memcpy(buf+6,c->player->id,8);
	memcpy(buf+14," ",1);
	memcpy(buf+15,score,4);
	memcpy(buf+19," ",1);
	memcpy(buf+20,ychar,3);
	memcpy(buf+23," ",1);
	memcpy(buf+24,xchar,3);
	memcpy(buf+27,"+++",3);
	multicast_send(buf,30,c->game);

	free(xchar);
	free(ychar);
	free(score);
}

void multicast_endga(struct game *g) {
	
	struct player *winner = game_get_winner(g);
	char *points = format_4digits(winner->score);

	char buf[100];
	memcpy(buf,"ENDGA ",6);
	memcpy(buf+6,winner->id,8);
	memcpy(buf+14," ",1);
	memcpy(buf+15,points,4);
	memcpy(buf+19,"+++",3);
	multicast_send(buf,22,g);

	free(points);
}
