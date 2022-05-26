#include "tasks.h"

int create_task(struct Liste *listTaskHead, struct timing *t, char **cmd, struct task *task) {
	int highest = -1;
		
		
	/* This is used to get the highest tasks ID available */
	/*		########################### 		*/
	DIR *dir = opendir("./tasks/");
	struct dirent *d;

	

	while((d = readdir(dir)) != NULL) {
		if(atoi(d->d_name) > highest)
			highest = atoi(d->d_name);
	}

	closedir(dir);
	/*		########################### 		*/


	//initializing the struct 
	task = malloc(sizeof(struct task));
	task->id = highest+1;
	task->exec_times = t;
	task->cmd = cmd;
	task->canRun = 1;

	task->next = NULL;
	addList(listTaskHead, task);

	char path[strlen("tasks/")+sizeof(int)];
	sprintf(path,"tasks/%d",highest+1);	//formatting directory path
	mkdir(path,0744);			//creating directory	(execution permission is required to create files inside a directory)

	char path_tmp[256];				//tmp char* to hold paths

	sprintf(path_tmp,"%s/cmd",path);		//formatting path
	int cmd_file_fd = open(path_tmp,O_WRONLY | O_CREAT,0644);	//creating file + opening it
	int i = 0;
	while(cmd[i] != NULL) {					//write all cmd args in the file
		dprintf(cmd_file_fd,"%s ",cmd[i]);
		i++;
	}
	close(cmd_file_fd);


	sprintf(path_tmp,"%s/exec_time",path);
	int exec_time_file_fd = open(path_tmp,O_WRONLY | O_CREAT,0644);
	char tmp[13];
	memcpy(tmp,&(t->minutes),8);
	memcpy(tmp+8,&(t->hours),4);
	memcpy(tmp+12,&(t->daysofweek),1);
	write(exec_time_file_fd,tmp,13);
	close(exec_time_file_fd);

	//only creating the files needed for later
	sprintf(path_tmp,"%s/stdout",path);
	int stdout_file_fd = open(path_tmp,O_RDONLY | O_CREAT,0644);
	close(stdout_file_fd);

	sprintf(path_tmp,"%s/stderr",path);
	int stderr_file_fd = open(path_tmp,O_RDONLY | O_CREAT,0644);
	close(stderr_file_fd);

	sprintf(path_tmp,"%s/times_exit-code",path);
	int te_file_fd = open(path_tmp,O_WRONLY | O_CREAT,0644);
	
	uint32_t size = 0; 
	write(te_file_fd,&size,4);
	close(te_file_fd);
	
	//printList(listTaskHead);

	return highest+1;
}

int delete_task(struct Liste *listTaskHead, int taskId){
	struct task *courant = listTaskHead->premier;

	if(courant == NULL){
		return -1; // la liste est vide
	}else{
		if(courant->id == taskId){
			deleteFileAndRep(taskId);
			listTaskHead->premier=courant->next;
			return 0;
		}

		while (courant->next != NULL)
		{
			struct task *targetTask = courant->next;

			if(targetTask->id == taskId){
					//suprimer le fichier
					deleteFileAndRep(taskId);
					courant->next = targetTask->next;
					return 0; // trouvé
			}
			//on avance
			courant = courant->next;
		} 

	}
	return -1;
}

void deleteFileAndRep(int taskId){
	char buf[strlen("tasks/") + sizeof(int)];
	sprintf(buf, "%s%d", "tasks/", taskId);
	int rem;
	char tmp[17];

	sprintf(tmp, "%s%s", buf, "/cmd");
	rem = unlink(tmp);

	sprintf(tmp, "%s%s", buf, "/exec_time");
	rem = unlink(tmp);
					
	sprintf(tmp, "%s%s", buf, "/stderr");
	rem = unlink(tmp);
					
	sprintf(tmp, "%s%s", buf, "/stdout");
	rem = unlink(tmp);
					
	sprintf(tmp, "%s%s", buf, "/times_exit-code");
	rem = unlink(tmp);

	rem = rmdir(buf); 
	assert(rem >= 0);
}

struct timing *get_current_timing() {

	int ti = time(NULL);
	int day = (ti / 86400 + 4) % 7;	//+ 4 because 01/01/1970 was a thursday
	int hour = (ti / 3600) % 24 + 1;	//+1 to have UTC +1 time
	int minute = (ti / 60) % 60;

	uint8_t day_b = (0x01 << day);

	uint32_t hours_b = (0x00000001 << hour);
	hours_b = htobe32(hours_b);

	uint64_t minutes_b = (0x0000000000000001 << minute);
	minutes_b = htobe64(minutes_b);

	struct timing *t = malloc(sizeof(struct timing));
	t->minutes = minutes_b;
	t->hours = hours_b;
	t->daysofweek = day_b;

	return t;
}

int task_should_run(struct task *ta) {
	struct timing *now = get_current_timing();
	int boolean = 1;

	//Testing minutes
	if((ta->exec_times->minutes & now->minutes) == 0)
		boolean = 0;

	//Testing hours
	if((ta->exec_times->hours & now->hours) == 0)
		boolean = 0;
	
	//Testing days
	if((ta->exec_times->daysofweek & now->daysofweek) == 0)
		boolean = 0;
	
	free(now);
	return boolean;
}

int execute_task(struct task *task) {
	int pid = fork();
	if(pid == -1)
		return -1;
	if(pid != 0)
		return pid;
	
	char buf[strlen("tasks/") + sizeof(int)];
	sprintf(buf,"%s%d","tasks/",task->id);

	char tmp[strlen("tasks/") + sizeof(int) + 16];

	sprintf(tmp,"%s%s",buf,"/times_exit-code");
	int tx = open(tmp,O_RDWR);

	uint32_t runCount = 0;
	read(tx,&runCount,4);
	runCount = htobe32(be32toh(runCount)+1);
	lseek(tx,0,SEEK_SET);
	write(tx,&runCount,4);

	lseek(tx,0,SEEK_END);
	int64_t timeNow = htobe64(time(NULL));
	write(tx,&timeNow,8);

	uint16_t exitCode = 0xFFFF;
	write(tx,&exitCode,2);

	close(tx);
	
	sprintf(tmp,"%s%s",buf,"/stdout");
	int stdout_fd = open(tmp,O_WRONLY);

	sprintf(tmp,"%s%s",buf,"/stderr");
	int stderr_fd = open(tmp,O_WRONLY);
	
	dup2(stdout_fd,1);
	dup2(stderr_fd,2);

	execvp(task->cmd[0],task->cmd);
	exit(-1);
}

void writeTaskExitCode(uint8_t code, int id) {
	
		char path[strlen("tasks/")+ sizeof(int) + strlen("/times_exit-code") + 1 ];
		sprintf(path,"%s%d%s","tasks/",id,"/times_exit-code");
		printf("%d\n",code);

		int tx_file_fd = open(path,O_WRONLY);
		lseek(tx_file_fd,-2,SEEK_END);
		uint16_t return_code = 0;
		memcpy(&return_code,&code,1);
		return_code = htobe16(return_code);

		write(tx_file_fd,&return_code,2);
		close(tx_file_fd);
}	

void addLaunchedTask(struct LaunchedTaskHead *h, struct LaunchedTask *t) {
	struct LaunchedTask *current = h->head;

	if(current == NULL) {
		h->head = t;
	} else {
		while(current->next != NULL)
			current = current->next;
		current-> next = t;
	}
}

void removeLaunchedTask(struct LaunchedTaskHead *h, struct LaunchedTask *t) {
	struct LaunchedTask *current = h->head;

	if(current == t) {
		h->head = t->next;
		free(t);
		return;
	}

	while(current->next != t) {
		current = current->next;
	}
	struct LaunchedTask *found = current->next;
	current->next = current->next->next;
	free(found);
}

struct task *getTaskByID(struct Liste *h, int id) {
	struct task *t = h->premier;
	while(t->id != id)
		t = t->next;
	return t;
}

void printLaunchedTasks(struct LaunchedTaskHead *h) {
	struct LaunchedTask *current = h->head;
	while(current != NULL) {
		printf("%d -> ",current->id);
		current = current->next;
	}
	printf("NULL");
}

void addList(struct Liste *listTaskHead, struct task *task){
		struct task *courant = listTaskHead->premier;

		if(courant == NULL) {
			listTaskHead->premier = task;		
		}else{
			while (courant->next != NULL)courant = courant->next;
			courant->next = task;
		}		
}

void printList(struct Liste *listTaskHead){
	
		struct task *courant = listTaskHead->premier;
		if(courant == NULL){
			printf("Votre liste est vide \n");
			return;
		}

		while (courant != NULL)
		{
			printf("[task %d ] -> ", courant->id);
			courant = courant->next;
		}
		printf("| NULL ] Fin de liste \n");
}

int task_exist(struct Liste *listTaskHead, int taskId){
	struct task *courant = listTaskHead->premier;

	if(courant == NULL){
		return -1; // la liste est vide
	}else{
		if(courant->id == taskId)return 0;
		while (courant->next != NULL)
		{
			struct task *targetTask = courant->next;
			if(targetTask->id == taskId)return 0; 
			//on avance
			courant = courant->next;
		} 

	}
	return -1;
}
