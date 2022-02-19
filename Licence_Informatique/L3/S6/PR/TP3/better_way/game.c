#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <assert.h>
#include <time.h>

int main(void) {

	int sock = socket(PF_INET,SOCK_STREAM,0);
	struct sockaddr_in server_sock;
	server_sock.sin_family=AF_INET;
	server_sock.sin_port = htons(12345);
	server_sock.sin_addr.s_addr=htonl(INADDR_ANY);

	int ret = bind(sock,(struct sockaddr *) &server_sock,sizeof(server_sock));
	assert(ret >= 0);

	ret = listen(sock,0);
	assert(ret >= 0);

	struct sockaddr_in caller;
	socklen_t socksize = sizeof(caller);

	int client_sock = accept(sock,(struct sockaddr *) &caller,&socksize);
	assert(client_sock >= 0);

	srand(time(NULL));
	int random = rand() % 65535;
	int remaining_tries = 20;

	while(remaining_tries > 0) {
		char buf[6];
		ret = recv(client_sock,buf,6,0);
		buf[ret-1] = '\0';
		int try = atoi(buf);
		if(try == random) {
			ret = send(client_sock,"Bien joué !",11,0);
			assert(ret >= 0);
			close(client_sock);
			return EXIT_SUCCESS;
		} else if(try < random) {
			ret = send(client_sock,"C'est plus",10,0);
			assert(ret >= 0);
		} else {
			ret = send(client_sock,"C'est moins",12,0);
			assert(ret >= 0);
		}
	}

	return EXIT_SUCCESS;
}
