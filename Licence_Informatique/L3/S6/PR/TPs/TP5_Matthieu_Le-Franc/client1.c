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
  adress_sock.sin_port = htons(atoi(argv[1])); //Récupération du numéro de port (argument passé au lancement)
  inet_aton("lulu", &adress_sock.sin_addr);

  //Pour la générer un random uint32_t
  srand(time(NULL)); 
  int sock;

  // création de 5 clients
  for(int i = 0 ; i < 5 ; i++){
    sock = socket(PF_INET, SOCK_STREAM, 0);
    int ret = connect(sock, (struct sockaddr *)&adress_sock, sizeof(struct sockaddr_in));
    printf("\n#################### Nouveau client ####################\n");

    // taille des pseudo = 10 mais peut aussi être inférieur
    char *pseudo_list[5] = {"Louismmmmm", "Paulmmmmmm", "Lauriemmmm", "Alicemmmmm", "Hugommmmmm"};
    char name[MAX_NAME];
    strcpy(name, pseudo_list[i]);

    if(ret != -1){
      
      send(sock,name,strlen(name)*sizeof(char),0);
      printf("Envoie du pseudo : %s\n", name);

      //  attente de la réponse "HELLO <pseudo>"
      char reponse_hello_pseudo[MAX_NAME + 7];
      int size_rec = recv(sock, reponse_hello_pseudo, (MAX_NAME + 7)*sizeof(char), 0);
      reponse_hello_pseudo[size_rec] = '\0';
      printf("Réception : %s\n", reponse_hello_pseudo);
      

      /*
       *
       * Requête INT<val>
       *
       */

      // Génération du nombre aléatoire à envoyer (sur 2 octets, soit 16 bits, soit 2^16 : un nombre compris [0 ; 65535])
      uint16_t val = rand() % 65535;
      val = htons(val);

      //Création du message + envoi de la requête
      char send_int_val[7];
      memcpy(send_int_val, "INT ", 4);
      memcpy(send_int_val+4, &val, 2);
      send_int_val[7] = '\0';
      printf("Envoie : INT%d\n", (unsigned short)val);
      send(sock,send_int_val,strlen(send_int_val), 0);

      // ******** Attente de confirmation de la reception  ******** //
      char reponse_intok[6];
      size_rec = recv(sock, reponse_intok, (6)*sizeof(char), 0);
      reponse_intok[size_rec] = '\0';
      printf("Confirmation reception de la requête INT<val> : %s\n", reponse_intok);

      // Après l'envoi du message le client se déconnecte
      printf("Fin de la connection avec %s, deconnexion\n", name);
      close(sock);
    
    } 
    
    else{
      perror("Erreur de connexion du client 1");
      close(sock);
      exit(1);
    }

  }

  close(sock);

  return EXIT_SUCCESS;
}
