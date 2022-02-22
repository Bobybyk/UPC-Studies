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
#include <pthread.h>

#define PORT 4296
#define MAX_NAME 10
#define SA struct sockaddr
pthread_mutex_t lock= PTHREAD_MUTEX_INITIALIZER;

int max_int;


char* max_request() {

}

void *communication(void *arg) {

    /*
     *
     * synhronise l'écriture "avec des autres threads"
     * pthread_mutex_lock(&lock);
     * 
     * désynhronise l'écriture "avec des autres threads"
     * pthread_mutex_unlock(&lock);
     * 
     */

    int ret = -1;
    int socket_comm=*((int *)arg);
    char buf_message[MAX_NAME];
    char pseudo[MAX_NAME];

    inet_ntoa(socket_comm.sin_addr);

    while(1) {

        if (sizeof(pseudo) == 0) {
            ret = recv(socket_comm,pseudo,MAX_NAME,0);
            assert(ret >= 0);
            continue;
        }

        ret = recv(socket_comm,buf_message,MAX_NAME,0);
        assert(ret >= 0);

        if (strncmp(buf_message, "INT", 3) == 0) {
            ret = sscanf("INTOK", MAX_NAME, buf_message);
            if (ret != 5) {
                printf("buffer writing error\n");
                exit(0);
            }
        }

        if (strncmp(buf_message, "MAX", 3) == 0) {

        }
            
        ret = send(socket_comm,buf_message,ret,0);

        if (ret != 0) {
            printf("error sending message\n");
            exit(0);
        }
    }

}

int main(void) {

	int sock = socket(PF_INET,SOCK_STREAM,0);

	struct sockaddr_in server_socket;	
	server_socket.sin_family = AF_INET;
	server_socket.sin_port= htons(PORT);
	server_socket.sin_addr.s_addr=htonl(INADDR_ANY);

    memset(server_socket, 0, sizeof(server_socket));

	int ret = bind(sock,(SA*) &server_socket,sizeof(server_socket));
	assert(ret >= 0);

	ret = listen(sock,0);
	assert(ret >= 0);

	struct sockaddr_in caller;
	socklen_t socket_size = sizeof(caller);

    while(1) {

        int *server_socket_bis = (int *)malloc(sizeof(int));
		*server_socket_bis = accept(sock, (SA*)&caller, &socket_size);
		
        if(*server_socket_bis >=0) {
			pthread_t th;	
			pthread_create(&th, NULL, communication, &server_socket_bis);
			pthread_join(th, NULL);
		}

    }

    // penser à close les sockets

	return EXIT_SUCCESS;

}