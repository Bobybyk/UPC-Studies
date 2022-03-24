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

int main() {
    
	int sock = socket(PF_INET, SOCK_DGRAM, 0);

	struct sockaddr_in server_socket;	
	server_socket.sin_family = AF_INET;
	server_socket.sin_port= htons(1717);
	server_socket.sin_addr.s_addr=htonl(INADDR_ANY);

    /*
     * OU
     * struct addrinfo hints;
     * bzero(&hints, sizeof(struct addrinfo));
     * hints.ai_family = AF_INET;
     * hints.ai_socktype=SOCK_DGRAM;
     * struct addrinfo *first_info;
     * int r=getaddrinfo("localhost","1717",&hints,&first_info);
     */

	int ret = bind(sock,(struct sockaddr*) &server_socket,sizeof(server_socket));
	assert(ret >= 0);

    struct sockaddr_in emet;
    socklen_t a=sizeof(emet);
    char msg_rcv[100];
    char msg_snd[22];
    memcpy(msg_snd, "ça va toi ? Moi ouais\0", 22);

    struct sockaddr_in client_socket;

    while(1){
        int rec=recvfrom(sock,msg_rcv,100,0,(struct sockaddr *)&emet,&a);
        msg_rcv[rec]='\0';
        printf("Message recu : %s\n", msg_rcv);
        client_socket.sin_port = emet.sin_port;
        client_socket.sin_addr = emet.sin_addr;
        printf("%s\n", msg_snd);
        if (strcmp(msg_rcv, "close") == 0) {
            break;
        }
        sendto(sock, msg_snd, 22, 0, (struct sockaddr *)&client_socket, (socklen_t)sizeof(struct sockaddr_in));
    }  
    close(sock);

}