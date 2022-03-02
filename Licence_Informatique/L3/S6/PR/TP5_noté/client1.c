#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <netdb.h>
#include <unistd.h>
#include <time.h>

#define MAX_NAME 10

int main(int argc, char *argv[]){
  (void) argc;
  struct sockaddr_in adress_sock;
  adress_sock.sin_family = AF_INET;
  adress_sock.sin_addr.s_addr = inet_addr("127.0.0.1");
  adress_sock.sin_port = htons(atoi(argv[1])); //Récupération du numéro de port par les arguments du main
  inet_aton("lulu", &adress_sock.sin_addr);

  srand(time(NULL)); //Pour la génération des nombres aléatoires
  int sock;

  for(int i = 0; i<5; i++){
    //Connexion au serveur
    sock = socket(PF_INET, SOCK_STREAM, 0);
    int ret = connect(sock, (struct sockaddr *)&adress_sock, sizeof(struct sockaddr_in));
    printf("\n*************** Nouveau pseudo ***************\n");

    //Variables importantes
    char *liste_de_pseudo[5] = {"Louismmmmm", "Paulmmmmmm", "Lauriemmmm", "Alicemmmmm", "Hugommmmmm"};
    char name[MAX_NAME];
    strcpy(name, liste_de_pseudo[i]);

    if(ret != -1){
      // ******** le client envoie directement son pseudo au serveur  ******** //
      send(sock,name,strlen(name)*sizeof(char),0);
      printf("Client envoie : %s\n", name);

      //  ******** Attente de la réponse du serveur : "HELLO <pseudo>"  ******** //
      char reponse_hello_pseudo[MAX_NAME + 7];
      int size_rec = recv(sock, reponse_hello_pseudo, (MAX_NAME + 7)*sizeof(char), 0);
      reponse_hello_pseudo[size_rec] = '\0';
      printf("Client reçoit : %s\n", reponse_hello_pseudo);
      

      // ******** Envoi de la requête INT<val> au serveur  ******** //
      // Génération du nombre aléatoire à envoyer
      uint32_t val = rand() % 65535;

      // ******** Création du message et envoi de la requête au serveur  ******** //
      char send_int_val[7];
      memcpy(send_int_val, "INT ", 4);
      memcpy(send_int_val+4, &val, 2);
      send_int_val[7] = '\0';
      printf("Client %s envoie : %s\n", name, send_int_val);
      send(sock,send_int_val,strlen(send_int_val), 0);
      // ******** Attente de confirmation de la reception  ******** //
      char reponse_intok[6];
      size_rec = recv(sock, reponse_intok, (6)*sizeof(char), 0);
      reponse_intok[size_rec] = '\0';
      printf("Serveur confirme la bonne reception de la requête INT<val> : %s\n", reponse_intok);

      // Après l'envoi du message le client se déconnecte
      printf("Fin du client %s, deconnexion :)\n", name);
      close(sock);
    }


    else{
      perror("Erreur de connexion du client 1.");
      exit(1);
    }
  }

  close(sock);

  return 0;
}
