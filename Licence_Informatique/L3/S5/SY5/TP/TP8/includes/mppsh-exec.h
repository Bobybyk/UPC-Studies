#define MAX_JOBS 64

#include "mppsh-parsing.h"

pid_t pid_jobs[MAX_JOBS];
char *argv_jobs[MAX_JOBS];
int nb_jobs;

void execute(char **argv, char *ref_in, char *ref_out, int flag);
int exec_external(int argc, char **argv, char *in_out[2], int flag, int bg);
int is_builtin(char *cmd);
int exec_builtin(int argc, char **argv, char *in_out[2], int flag);
void examine_bg ();
