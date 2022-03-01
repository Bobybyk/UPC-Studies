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

#define PORT 4296
#define MAX_NAME 10
#define MAX_MSG 20
#define SA struct sockaddr
pthread_mutex_t lock= PTHREAD_MUTEX_INITIALIZER;

typedef struct data {
    int *socket;
    char *max_pseudo;
    int *max_int;
    char *ip;
} data;


void *communication(void *arg) {

    /* 
     * récupération des arguments de la structure donnée en argument
     * et initialisation des paramètres.
     */
    data *d = (data*) arg;
    int *socket_com = d -> socket;
    int *max_int = d -> max_int;
    char *max_pseudo = d -> max_pseudo;
    char *ip_com = d -> ip;

    // pseudo du client
    char name[MAX_NAME];
    int ret = recv(*socket_com, name, (MAX_NAME)*sizeof(char), 0);
    name[ret] = '\0';
    printf("Message reçu : %s\n", name);

    // réponse "Hello <pseudo>"
    char resp[MAX_NAME+7];
    strcpy(resp, "HELLO ");
    strcat(resp, name);
    printf("réponse : %s\n", resp);
    send(*socket_com, resp, strlen(resp)*sizeof(char), 0);


    // gestion message client
    char request[MAX_MSG];
    ret = recv(*socket_com, request, (MAX_MSG)*sizeof(char), 0);
    request[ret] = '\0';
    printf("Message reçu %s\n", request);

    // parsing pour savoir quel est le type de requête
    char *arg1;
    char *arg2;
    const char delim[2] = " ";
    arg1 = strtok(request, delim);

    if (strcmp(arg1, "INT") == 0) {
        arg2 = strtok(NULL, delim);
        char *arg2_bis = malloc(sizeof(char) * strlen(arg2));
        strcpy(arg2_bis, arg2);

        int check_val = 1;
        int received_integer;
        for (long unsigned i = 0 ; i<strlen(arg2) ; i++) {
            if (isdigit(*arg2_bis)) {
                continue;
            } else {
                check_val = 0;
            }
            arg2_bis += 1;
        }

        if (check_val == 0) {
            goto end;
        }
        
        received_integer = atoi(arg2);
        if (received_integer >= *max_int) {
            
            pthread_mutex_lock(&lock);
            
            *max_int = received_integer;
            strcpy(max_pseudo, name);
            
            pthread_mutex_unlock(&lock);
        }

        send(*socket_com, "INTOK", strlen("INTOK")*sizeof(char), 0);
        goto end;
    }

    if (strcmp(arg1, "MAX") == 0) {
        char resp_req[MAX_MSG];

        int max = *max_int;
        char max_string[MAX_MSG];

        if (max == -1) {
            strcpy(resp_req, "NOP");
        } else {
            strcpy(resp_req, "REP");
            strcat(resp_req, max_pseudo);
            strcat(resp_req, ip_com);
            strcat(resp_req, max_string);
        }

        printf("réponse : %s\n", resp_req);
        send(*socket_com, resp_req ,strlen(resp_req)*sizeof(char),0);
    }
    
    end:
        close(*socket_com);
        return NULL;

    /*
     *
     * synhronise l'écriture "avec des autres threads"
     * pthread_mutex_lock(&lock);
     * 
     * désynhronise l'écriture "avec des autres threads"
     * pthread_mutex_unlock(&lock);
     * 
     */

    /* int ret = -1;
    int socket_comm=*((int *)arg);
    char buf_message[MAX_NAME];
    char pseudo[MAX_NAME];

    //inet_ntoa(socket_comm.sin_addr);

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
    } */

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

    int *max_int = (int *) malloc(sizeof(int));
    *max_int = -1;

    while(1) {

        int *server_socket_bis = (int *)malloc(sizeof(int));
		*server_socket_bis = accept(sock, (SA*)&caller, &socket_size);
		
        if(*server_socket_bis >=0) {
            data *d = malloc(sizeof(data));
            d -> socket = server_socket_bis;
            d -> max_pseudo = NULL;
            d -> ip = inet_ntoa(caller.sin_addr);

			pthread_t th;	
            // passer la structure en argument plutôt que le descripteur...
			pthread_create(&th, NULL, communication, d);
			pthread_join(th, NULL);

            free(d);
            close(*server_socket_bis);
		}
    }

    // penser à close les sockets
    free(max_int);
    close(sock);
	return EXIT_SUCCESS;

}