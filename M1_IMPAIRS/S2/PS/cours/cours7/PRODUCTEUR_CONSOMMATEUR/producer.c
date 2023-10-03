#define  _XOPEN_SOURCE 500
#include <pthread.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <sys/mman.h>
#include <sys/stat.h>
#include <unistd.h>
#include <stdio.h>

#include "panic.h"
#include "prefix_slash.h"
#include "memory.h"
#include "thread_error.h"
#include "open_memory.h"
#define LEN 256

/*  get_data() returns new data value.
    La première valeur de valeue choisie de façon aléatoire,
    les valeurs suivante obtenu simplement par incrémentation
    de value.
 */
int get_data(){
  static bool init = true;
  static int value;
  if( init ){
    srandom( getpid() );
    init = false;
    value = random();
  }
  return ++value;
}


int main(int argc, char **argv){
  if(argc != 2){
    fprintf(stderr,"usage : %s shared_memory_object\n", argv[0]);
    exit(1);
  }
  
  memory *mem = open_memory( argv[1] ); 
  
  while( 1 ){
    int code;

    /*  mutex_lock */
    if( (code = pthread_mutex_lock(&mem->mutex) ) != 0 ){
      thread_error( __FILE__ , __LINE__ , code, "mutex_lock" );
    }
    fprintf(stderr, "process %d mutex locked\n", (int) getpid());
    
    /* attendre jusqu'à ce que les données précédentes soient
    * consommées */    
    while( mem->is_new_value )
      if( ( code = pthread_cond_wait( &mem->wcond, &mem->mutex) ) > 0 ){
	fprintf(stderr, "process %d error\n", (int) getpid());
	thread_error( __FILE__, __LINE__, code, "pthread_cond_wait" );
      }
    fprintf(stderr, "process %d memory modification\n", (int) getpid());
    mem->pid = getpid();
    mem->data = get_data();
    mem->is_new_value = true; /* indiquer que data contient
			       * une nouvelle valeur */ 

    if( msync(mem, sizeof(memory), MS_SYNC) < 0 )
      PANIC_EXIT("msync");
    
    if( ( code = pthread_mutex_unlock(&mem->mutex)) != 0)
      thread_error( __FILE__ , __LINE__ , code, "mutex_lock" );
    
    if( ( code = pthread_cond_signal( &mem->rcond ) ) > 0 )
      thread_error( __FILE__ , __LINE__ , code, "mutex_lock" );
    fprintf(stderr, "process %d leaves the critical section\n", (int) getpid());
    sleep(1); /* pour simuler un travail supplementaire en dehors
	       de la section critique */
  }	
}
