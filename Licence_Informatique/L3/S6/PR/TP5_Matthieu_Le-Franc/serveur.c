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
#include <string.h>
#include <ctype.h>

#define MAX_NAME 10
#define MAX_MSG 20
#define SA struct sockaddr
pthread_mutex_t lock= PTHREAD_MUTEX_INITIALIZER;

// données relatives à l'entier max et son client associé
char *max_pseudo;
uint16_t max_int;
u_int32_t ip_addr_max = 0;

// structure comportant les information du client courant
typedef struct client {
    int *socket;
    uint32_t ip;
} client;


void *communication(void *arg) {

    /* 
     * récupération des arguments de la structure donnée en argument
     * et initialisation des paramètres.
     */
    client *cli = (client*) arg;
    int *socket_com = cli -> socket;
    uint32_t ip_com = cli -> ip;

    // pseudo du client
    char name[MAX_NAME];
    int ret = recv(*socket_com, name, (MAX_NAME)*sizeof(char), 0);
    name[ret] = '\0';
    printf("Message reçu : %s\n", name);

    // réponse "Hello <pseudo>"
    char resp[MAX_NAME+7];
    memcpy(resp, "HELLO ", 6);
    memcpy(resp+6, name, MAX_NAME);
    resp[MAX_NAME+6] = '\0';
    printf("réponse : %s\n", resp);
    send(*socket_com, resp, strlen(resp)*sizeof(char), 0);


    // gestion message client
    char request[MAX_MSG];
    ret = recv(*socket_com, request, (MAX_MSG)*sizeof(char), 0);
    request[ret] = '\0';

    // récupération du premier mot (pour connaitre la requête)
    char arg1[3+1];
    uint16_t arg2;

    memcpy(arg1, request, 3);
    arg1[4] = '\0';
    
    if (strcmp(arg1, "INT") == 0) {
        
        memcpy(&arg2, request+4, 2);
        printf("Message reçu : INT %u\n", (unsigned short)ntohs(arg2));
        
        if (ntohs(arg2) >= max_int) {
            
            printf("Mise à jour max INT\n");
            pthread_mutex_lock(&lock);

            max_int = ntohs(arg2);
            memcpy(max_pseudo, name, MAX_NAME);
			memcpy(&ip_addr_max,&ip_com,4);

            pthread_mutex_unlock(&lock);

        }

        send(*socket_com, "INTOK", strlen("INTOK")*sizeof(char), 0);
        goto end;

    }

    if (strcmp(arg1, "MAX") == 0) {
        printf("Message reçu : MAX\n");
        char resp_req[MAX_MSG]; // 3+10+4+2+1
        printf("max int : %u\n", (unsigned short)max_int);
        if (max_int == 0) {
            ret = send(*socket_com, "NOP" , 3, 0);
			assert(ret >= 0);
        } else {
            memcpy(resp_req, "REP", 3);
            memcpy(resp_req+3, max_pseudo, 10);
            memcpy(resp_req+13, &ip_addr_max, 4);
            uint16_t maxToSend = htons(max_int);
            memcpy(resp_req+17, &maxToSend, 2);
            ret = send(*socket_com, resp_req , 19, 0);
            assert(ret >= 0);
        }
    }
    
    end:
        close(*socket_com);
        return NULL;

}

int main(int argc, char **argv) {
    
    (void) argc;
    
	int sock = socket(PF_INET,SOCK_STREAM,0);

	struct sockaddr_in server_socket;	
	server_socket.sin_family = AF_INET;
	server_socket.sin_port= htons(atoi(argv[1]));
	server_socket.sin_addr.s_addr=htonl(INADDR_ANY);

	int ret = bind(sock,(SA*) &server_socket,sizeof(server_socket));
	assert(ret >= 0);

	ret = listen(sock,0);
	assert(ret >= 0);

	struct sockaddr_in caller;
	socklen_t socket_size = sizeof(caller);

    max_int = 0;
    max_pseudo = (char *) malloc(sizeof(char));

    while(1) {

        int *server_socket_bis = (int *)malloc(sizeof(int));
		*server_socket_bis = accept(sock, (SA*)&caller, &socket_size);
		
        if(*server_socket_bis >=0) {

            client *cli = malloc(sizeof(client));
            cli -> socket = server_socket_bis;
            cli -> ip = caller.sin_addr.s_addr;

			pthread_t th;
			pthread_create(&th, NULL, communication, cli);
			pthread_join(th, NULL);

            free(cli);
            close(*server_socket_bis);

		}
    }

    close(sock);
	return EXIT_SUCCESS;

}