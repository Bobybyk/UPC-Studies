#define  _XOPEN_SOURCE 500
#include <sys/types.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/mman.h>
#include <fcntl.h>
#include <stdio.h>
#include <stdbool.h>

#include "prefix_slash.h"
#include "memory.h"
#include "panic.h"
#include "init_mutex.h"
#include "thread_error.h"
#include "open_memory.h"

void *open_memory(const char *mem_objet){

  bool  new_shm = true;  /* new_shm == true si la creation de nouveau
			     * shared memory object */
  /* ajouter '/' au debut du nom de shared memory object */
  char *shm_name = prefix_slash(mem_objet);

  // shm_unlink peut être necessaire sur MacOS
  // pour faire ftruncate
  //shm_unlink(shm_name);

  /* open and create */
  int fd = shm_open(shm_name, O_CREAT| O_RDWR | O_EXCL,
		    S_IWUSR | S_IRUSR);
  if( fd >= 0 ){
    //creation de shared memory object reussie.
    //fixer la taille de shared memory object
    //MacOS permet de modifier la taille objet mémoire
    //uniquement après la création quand sa taille est 0
    if( ftruncate( fd, sizeof( memory ) ) < 0 )
      PANIC_EXIT("ftruncate");
  }
  else if( fd < 0 && errno == EEXIST ){/* shared memory object existe déjà
				       * il suffit de l'ouvrir */
    fd = shm_open(shm_name,  O_RDWR, S_IWUSR | S_IRUSR);
    if( fd < 0 )
      PANIC_EXIT("shm_open existing object");
    new_shm = false;
    
  }else
    PANIC_EXIT("shm_open");


  
  /* projection de shared memory object dans
   * la mémoire                              */
  memory *mem = mmap(NULL, sizeof(memory), PROT_READ|PROT_WRITE,
		     MAP_SHARED, fd, 0);
  if( (void *)mem == MAP_FAILED )
    PANIC_EXIT("mmap");

  //initialiser la mémoire seulement si nouveau shared memory object est créé
  if( new_shm ){
    int code;
    mem->is_new_value = false;/* memoire est libre initialement */
    mem->data = -1;   /* les données, à priori inutile */ 
    if( ( code = initialiser_mutex(&mem->mutex) ) != 0)
      thread_error(__FILE__, __LINE__, code, "init_mutex");
    if( ( code = initialiser_cond(&mem->rcond) ) != 0 )
      thread_error(__FILE__, __LINE__, code, "init_rcond");
    if( ( code = initialiser_cond(&mem->wcond) ) != 0 )
      thread_error(__FILE__, __LINE__, code, "init_wcond");
  }
  return mem;
}
