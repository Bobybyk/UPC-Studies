#ifndef DATA_H
#define DATA_H
#define LEN 1024
struct mess{
  pid_t  pid;
  size_t len;
  char msg[LEN];
};
typedef struct mess mess;
#endif

