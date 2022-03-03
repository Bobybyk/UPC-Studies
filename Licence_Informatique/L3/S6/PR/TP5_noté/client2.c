#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <netdb.h>
#include <unistd.h>
#include <assert.h>

#define MAX_NAME 10
#define MAX 19

int main(int argc, char **argv){
	(void) argc;
	struct sockaddr_in adress_sock;
	adress_sock.sin_family = AF_INET;
	adress_sock.sin_addr.s_addr = inet_addr("127.0.0.1");
	adress_sock.sin_port =  htons(atoi(argv[1]));

	int ret = inet_aton("lulu", &adress_sock.sin_addr); 
	assert(ret >= 0);
	
	//Connexion au serveur
	int sock = socket(PF_INET, SOCK_STREAM, 0);
	ret = connect(sock, (struct sockaddr *)&adress_sock, sizeof(struct sockaddr_in));
	
	if (ret == -1) {
		perror("Erreur de connexion du client 2");
		goto end;
	}


	char name[MAX_NAME];
	memcpy(name, "Matthieu", 8);
	
	ret = send(sock, name, 8, 0);
	assert(ret >= 0);
	
	//Attente de la réponse du serveur : "HELLO <pseudo>"
	char reponse_hello_pseudo[MAX_NAME + 6];
	ret = recv(sock, reponse_hello_pseudo, MAX_NAME+6, 0);
	assert(ret >= 0);
	//reponse_hello_pseudo[size_rec] = '\0';
	
	ret = send(sock, "MAX", 3, 0);
	assert(ret >= 0);

	char resp_req[MAX];
	ret = recv(sock, resp_req, 19, 0);
	//resp_req[ret] = '\0';
	assert(ret >= 0);

	if (ret == 3) {
		printf("There is no max int in the server\n");
		goto end;
	}

	char pseudo[10+1];
	pseudo[10] = '\0';
	uint32_t ip;
	uint16_t max_int_val;

	memcpy(&pseudo, resp_req+3, 10);
	memcpy(&max_int_val, resp_req+17, 2);
	memcpy(&ip, resp_req+13, 4);

	struct in_addr struct_output;
	struct_output.s_addr = ip;
	char *ip_output = inet_ntoa(struct_output);

	printf("réponse : %s%d%s\n", pseudo, ntohs(max_int_val), ip_output);
		

	end:
		close(sock);
		return 0;
}
