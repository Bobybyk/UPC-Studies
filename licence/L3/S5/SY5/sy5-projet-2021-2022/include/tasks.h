#ifndef TASKS_H
#define TASKS_H

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <sys/types.h>
#include <dirent.h>
#include <sys/stat.h>
#include <unistd.h>
#include <fcntl.h>
#include <time.h>
#include <unistd.h> 

#include "utils/command_line.h"
#include "timing-text-io.h"

struct task {
	int id;
	char **cmd;
	struct timing *exec_times;
	struct task *next;
	int canRun;	
};

struct LaunchedTask {
	int id;
	int pid;
	struct LaunchedTask *next;
};

struct LaunchedTaskHead {
	struct LaunchedTask *head;
};

void addLaunchedTask(struct LaunchedTaskHead *head, struct LaunchedTask *t);
void removeLaunchedTask(struct LaunchedTaskHead *head, struct LaunchedTask *t);
void printLaunchedTasks(struct LaunchedTaskHead *head);

typedef struct Liste Liste;
struct Liste
{
    struct task *premier;
};

struct task *getTaskByID(struct Liste *h, int id);
int create_task(struct Liste *listTaskHead, struct timing *t, char **cmd, struct task *exec_times);
int delete_task(struct Liste *listTaskHead, int taskid);
int execute_task(struct task *task);
void writeTaskExitCode(uint8_t code, int id);
int task_should_run(struct task *ta);
void addList(struct Liste *listTaskHead, struct task *task);
void printList(struct Liste *listTaskHead);
struct timing *get_current_timing();
void deleteFileAndRep(int taskID);
int task_exist(struct Liste *listTaskHead, int taskId);

#endif
