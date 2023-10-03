#include <string.h>
#include <stdlib.h>
#include <assert.h>
#include "prefix_slash.h"

/* si name commence par / prefix_slash() retourne une copie de
   name;
   si name ne commence pas par / alors prefix_slash retourne
   le nom avec / ajoute au debut
*/

char *prefix_slash(const char *name){
  #define L_NAME 256
  //  static char nom[L_NAME]; 

  char *nom=malloc(L_NAME);
  assert(nom != NULL);
  
  if( name[0] != '/' ){
    nom[0] = '/';
    nom[1]='\0';
    strncat(nom, name, L_NAME-2);
  }else{
    strncpy(nom, name, L_NAME);
    nom[L_NAME-1]='\0';
  }
  return nom;
}
