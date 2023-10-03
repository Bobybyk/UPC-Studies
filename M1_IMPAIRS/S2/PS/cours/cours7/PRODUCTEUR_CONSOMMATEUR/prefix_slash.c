#include <string.h>
#include <stdlib.h>

#include "prefix_slash.h"

/* fonction prefix_slash ajoute '/' au debut de name si name
   ne commence pas par le caractère '/'
*/

char *prefix_slash(const char *name){
  #define L_NAME 256
  static char nom[L_NAME]; 

  if( name[0] != '/' ){
    nom[0] = '/';
    strncpy(nom+1, name, L_NAME-1);
  }else{
    strncpy(nom, name, L_NAME);
  }
  nom[L_NAME-1]='\0';
  return nom;
}
