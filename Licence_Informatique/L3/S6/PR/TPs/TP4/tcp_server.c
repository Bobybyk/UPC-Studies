#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <arpa/inet.h>
#include <string.h>
#include <stdio.h>
#include <assert.h>
#include <unistd.h>
#include <stdlib.h>
#include <pthread.h>

#define PORT 6666
#define MAX 80
#define SA struct sockaddr

void *communication(void *arg) {
	printf("client ggggggconnecté !\n");
	/* int socket_comm=*((int *)arg);
	char *message="Hello\n";
	int ret = send(socket_comm, message, strlen(message)*sizeof(char), 0);
	if(ret != -1) {
		printf("c pété");
	} */
	printf("c pété 2");
	/*char buff[MAX];
	int n = 0;
	while ((buff[n++] = getchar()) != '\n') {
		int rec = recv(socket_comm, buff, MAX*sizeof(char), 0);
		buff[rec]='\0';
	}
	printf("Message reçu : %s\n", buff);
	if ((strncmp(buff, "exit", 4)) == 0) {
		printf("Client Exit...\n");
		close(socket_comm);
	} */
	return NULL;
}

int main(int argc, char **argv) {
	struct sockaddr_in servaddr;
	int ret, socket_fd, socket_fd2;

	memset(&servaddr, 0, sizeof(servaddr));

	//socket creation/verification
	socket_fd = socket(AF_INET, SOCK_STREAM, 0);
    if (socket_fd == -1) {
        printf("socket creation failed...\n");
        exit(0);
    } else {
		printf("Socket successfully created..\n");
	}

	// assing IP, PORT
	servaddr.sin_family = AF_INET;
    servaddr.sin_addr.s_addr = htonl(INADDR_ANY);
    servaddr.sin_port = htons(PORT);

	// bindling newly created socket to given IP and verification
    ret = bind(socket_fd, (SA*)&servaddr, sizeof(servaddr));

	if (ret != 0) {
        printf("socket bind failed...\n");
        exit(0);
	} else {
		printf("Socket successfully binded..\n");
	}
	ret = listen(socket_fd, 5);
	if (ret != 0) {
	    printf("Listen failed...\n");
        exit(0);
	} else {
		printf("Server listening..\n");
		struct sockaddr_in caller;
		socklen_t si=sizeof(caller);
		while (1) {
			int *socket_fd2 = (int *)malloc(sizeof(int));
			*socket_fd2 = accept(socket_fd, (SA*)&caller, &si);
			if(*socket_fd2 >=0) {
				pthread_t th;	
				pthread_create(&th, NULL, communication, &socket_fd2);
				pthread_join(th, NULL);
			}
		}
		
	}
	close(socket_fd);
	close(socket_fd2);

	return 0;
}