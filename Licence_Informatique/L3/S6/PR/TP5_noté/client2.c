#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <netdb.h>
#include <unistd.h>



int main(){
	struct sockaddr_in adress_sock;
	adress_sock.sin_family = AF_INET;
	adress_sock.sin_port = htons(4296);
	inet_aton("lulu", &adress_sock.sin_addr); 
	
	
	//Connexion au serveur
	int sock = socket(PF_INET, SOCK_STREAM, 0);
	int r = connect(sock, (struct sockaddr *)&adress_sock, sizeof(struct sockaddr_in));
	
	
	//Variables importantes
	int MAX_NAME = 10;
	char name[MAX_NAME];
	sprintf(name, "bob");
	//strcat(name, "bob");
	
	if(r!=-1){
		//A la connexion, le client envoie directement le pseudo du client
		send(sock,name,strlen(name)*sizeof(char),0);
		printf("Client envoie : %s\n", name);
		
		//Attente de la réponse du serveur : "HELLO <pseudo>"
		char reponse_hello_pseudo[MAX_NAME + 7];
		int size_rec = recv(sock, reponse_hello_pseudo, (MAX_NAME + 7)*sizeof(char), 0);
		reponse_hello_pseudo[size_rec] = '\0';
		printf("Serveur répond : %s\n", reponse_hello_pseudo);
		
		
		//Deux messages possibles :
		// 1 : "INT <val>" 
		// 2 : "MAX" pour demmander le plus grand entier
		
		// Après l'envoi du message le client se déconnecte
	}
	
	else{
		perror("Erreur de connexion du client 1.");
		exit(1);
	}
		
	close(sock);
	
	return 0;
}
