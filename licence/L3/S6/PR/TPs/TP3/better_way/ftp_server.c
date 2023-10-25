#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <assert.h>
#include <time.h>
#include <fcntl.h>

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

	ret = send(client_sock,"DEBUT\n",6,0);
	assert(ret >= 0);

	int length = 0;
	char buf[100];
	ret = recv(client_sock,buf,100,0);
	buf[ret -1] = '\0';
	assert(ret >= 0);

	length = atoi(buf);	

	int file_fd = open("data.txt",O_CREAT | O_WRONLY | O_TRUNC,0644);
	for(int i=0;i<length;i++) {
		ret = recv(client_sock,buf,100,0);	
		assert(ret > 0);

		ret = write(file_fd,buf,ret);
		assert(ret > 0);
	}
	ret = send(client_sock,"BIEN RECU\n",10,0);
	assert(ret >= 0);

	close(file_fd);
	close(client_sock);

	return EXIT_SUCCESS;
}
