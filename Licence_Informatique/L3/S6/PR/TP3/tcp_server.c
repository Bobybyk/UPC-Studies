#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <arpa/inet.h>
#include <string.h>
#include <stdio.h>
#include <assert.h>
#include <unistd.h>

#define PORT 6666
#define MAX 80
#define SA struct sockaddr

void listener(int conn_fd) {
	char buff[MAX];

	while(1) {
		bzero(buff, MAX);
		read(conn_fd, buff, sizeof(buff));
		write(conn_fd, buff, sizeof(buff));
	}
	close(conn_fd);
	
}

int main(int argc, char **argv) {
	struct sockaddr_in servaddr, cli;
	int ret, socket_fd, conn_fd, len;

	//socket creation/verification
	socket_fd = socket(AF_INET, SOCK_STREAM, 0);
	assert(socket_fd >= 0);
	bzero(&servaddr, sizeof(servaddr));

	// assing IP, PORT
	servaddr.sin_family = AF_INET;
    servaddr.sin_addr.s_addr = htonl(INADDR_ANY);
    servaddr.sin_port = htons(PORT);

	// bindling newly created socket to given IP and verification
    ret = bind(socket_fd, (SA*)&servaddr, sizeof(servaddr));

	if (ret == 0) {
		ret = listen(socket_fd, 0);
		if (ret != 0) {
			len = sizeof(cli);
			conn_fd = accept(socket_fd, (SA*)&cli, &len);
			if (conn_fd > -1) {
				listener(conn_fd);
			}
		}
	}
	close(socket_fd);
	return 0;
}