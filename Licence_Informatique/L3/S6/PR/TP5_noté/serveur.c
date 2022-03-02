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

#define PORT 4297
#define MAX_NAME 10
#define MAX_MSG 20
#define SA struct sockaddr
pthread_mutex_t lock= PTHREAD_MUTEX_INITIALIZER;

char *max_pseudo;
uint32_t max_int;

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
    printf("Message reçu %s\n", request);

    // parsing pour savoir quel est le type de requête
    char arg1[3+1];
    uint32_t arg2;
    //const char delim[2] = " ";
    //arg1 = strtok(request, delim);

    memcpy(arg1, request, 3);
    arg1[4] = '\0';
    if (strcmp(arg1, "INT") == 0) {
        memcpy(&arg2, request+4, 2);
        printf("%lu\n", (unsigned long)arg2);
        if (arg2 >= max_int) {
            printf("ok\n");
            pthread_mutex_lock(&lock);

            max_int = arg2;
            strcpy(max_pseudo, name);

            pthread_mutex_unlock(&lock);

        }

        send(*socket_com, "INTOK", strlen("INTOK")*sizeof(char), 0);
        goto end;
    }

    if (strcmp(arg1, "MAX") == 0) {
        char resp_req[20]; // 3+10+4+2

        int max = max_int;
        char val[2];
        char ip_str[4];
        sprintf(val, "%d", max);
        sprintf(ip_str, "%d", ip_com);
        if (max == -1) {
            strcpy(resp_req, "NOP");
        } else {
            /* strcpy(resp_req, "REP");
            strcat(resp_req, );
            strcat(resp_req, ip_str);
            strcat(resp_req, val); */
            memcpy(resp_req, "REP", 3);
            memcpy(resp_req+3, max_pseudo, 10);
            memcpy(resp_req+13, &ip_com, 4);
            memcpy(resp_req+17, &val, 2);
            resp_req[19] = '\0';
        }

        printf("réponse : %s\n", resp_req);
        send(*socket_com, resp_req ,strlen(resp_req)*sizeof(char),0);
    }
    
    end:
        close(*socket_com);
        return NULL;

}

int main(void) {

	int sock = socket(PF_INET,SOCK_STREAM,0);

	struct sockaddr_in server_socket;	
	server_socket.sin_family = AF_INET;
	server_socket.sin_port= htons(PORT);
	server_socket.sin_addr.s_addr=htonl(INADDR_ANY);

	int ret = bind(sock,(SA*) &server_socket,sizeof(server_socket));
	assert(ret >= 0);

	ret = listen(sock,0);
	assert(ret >= 0);

	struct sockaddr_in caller;
	socklen_t socket_size = sizeof(caller);

    //*max_int = (int *) malloc(sizeof(int));
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