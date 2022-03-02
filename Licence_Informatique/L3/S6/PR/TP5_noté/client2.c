#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <netdb.h>
#include <unistd.h>

#define MAX_NAME 10
#define MAX 20

int main(){
	struct sockaddr_in adress_sock;
	adress_sock.sin_family = AF_INET;
	adress_sock.sin_addr.s_addr = inet_addr("127.0.0.1");
	adress_sock.sin_port = htons(4297);
	inet_aton("lulu", &adress_sock.sin_addr); 
	
	//Connexion au serveur
	int sock = socket(PF_INET, SOCK_STREAM, 0);
	int ret = connect(sock, (struct sockaddr *)&adress_sock, sizeof(struct sockaddr_in));
	
	//Variables importantes
	char name[MAX_NAME];
	sprintf(name, "Jean");
	
	if(ret != -1){
		//A la connexion, le client envoie directement le pseudo du client
		send(sock,name,strlen(name)*sizeof(char),0);
		printf("Client envoie : %s\n", name);
		
		//Attente de la réponse du serveur : "HELLO <pseudo>"
		char reponse_hello_pseudo[MAX_NAME + 7];
		int size_rec = recv(sock, reponse_hello_pseudo, (MAX_NAME + 7)*sizeof(char), 0);
		reponse_hello_pseudo[size_rec] = '\0';
		printf("Serveur répond : %s\n", reponse_hello_pseudo);
		
		send(sock, "MAX", 3, 0);
		char resp_req[MAX];
		ret = recv(sock, resp_req, sizeof(resp_req), 0);
		resp_req[ret] = '\0';
		printf("Serveur répond : %s\n", resp_req);
		char val[12];
		memcpy(val, resp_req+3, 10);
		memcpy(val+10, resp_req+17, 2);
		printf("%s\n", val);
	}
	
	else{
		perror("Erreur de connexion du client 2.");
		exit(1);
	}
		
	close(sock);
	
	return 0;
}
