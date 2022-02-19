#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <arpa/inet.h>
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <unistd.h>

int main(int argc, char **argv) {

	int sock = socket(PF_INET,SOCK_STREAM,0);

	struct sockaddr_in server_sock;	
	server_sock.sin_family = AF_INET;
	server_sock.sin_port= htons(12345);
	server_sock.sin_addr.s_addr=htonl(INADDR_ANY);

	int ret = bind(sock,(struct sockaddr *) &server_sock,sizeof(server_sock));
	assert(ret >= 0);

	ret = listen(sock,0);
	assert(ret >= 0);

	struct sockaddr_in caller;
	socklen_t sock_size = sizeof(caller);

	while(1) {
		int client_sock = accept(sock,(struct sockaddr *) &caller,&sock_size);

		char buf[256];
		ret = recv(client_sock,buf,256,0);
		assert(ret >= 0);

		ret = send(client_sock,buf,ret,0);
		assert(ret >= 0);
		close(client_sock);
	}

	return EXIT_SUCCESS;
}
