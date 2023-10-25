#ifndef MEMORY_H
#define MEMORY_H
#include <pthread.h>
#include <stdbool.h>
typedef struct{
  pthread_mutex_t mutex;
  pthread_cond_t  rcond;
  pthread_cond_t  wcond;
  pid_t  pid;
  bool is_new_value; /* is_new_value == true quand data
			 *contient nouvelle valeur écrite par
			 * a producer */
  int data;
} memory;  
#endif

