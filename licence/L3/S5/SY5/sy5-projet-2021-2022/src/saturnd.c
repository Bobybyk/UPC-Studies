#include "saturnd.h"

int self_pipe[2];

void handler(int sig) {
	write(self_pipe[1],"a",1);	//if a child terminated, write a byte into the self pipe to wake poll() up
}

int main(int argc, char **argv) {
	
	daemonize();

	char *username = getlogin();
	
	char path[strlen("/tmp/") + strlen(username) + strlen("/saturnd/pipes/saturnd-request-pipe") + 1]; 
	sprintf(path,"%s%s%s","/tmp/",username,"/saturnd/pipes/saturnd-request-pipe");

	int req_fd = open(path,O_RDWR);		//open the requet pipe as reader AND writer to avoid raising POLLHUP with poll
	if(req_fd < 0)
		goto error;

	struct pollfd pfd[2];		//fill the structure needed for poll down below
	
	memset(&pfd[0],0,sizeof(struct pollfd));	//filling for the request pipe
	pfd[0].fd = req_fd;
	pfd[0].events = POLLIN;

	pipe(self_pipe);
	memset(&pfd[1],0,sizeof(struct pollfd));	//creating pollfd for the self pipe
	pfd[1].fd = self_pipe[0];
	pfd[1].events = POLLIN;

	struct sigaction sa;				//creating sigaction to handle childs terminating 
	memset(&sa,0,sizeof(struct sigaction));
	sa.sa_handler = handler;			

	sigaction(SIGCHLD,&sa,NULL);			//applying the sigaction

//Pour l'instant je mets la tete de list ici
	Liste *listTaskHead = malloc(sizeof(Liste));
	listTaskHead->premier = NULL;

	struct LaunchedTaskHead *lth = malloc(sizeof(struct LaunchedTaskHead));
	lth->head = NULL;

	while(1) {
		int time_remaining = (60 - (time(NULL) % 60)) * 1000;
		poll(pfd,2,time_remaining);			//wait for the client to write on the pipe

		if(pfd[0].revents & POLLIN) {
			int ret = read_request(req_fd, listTaskHead);		//read the request written
			if(ret == -1)				//error detected
				goto error;
			else if (ret == 1)			//daemon kill request detected
				goto terminate;
		}

		if(pfd[1].revents & POLLIN) {		//if a child has terminated
			char tmp[PIPE_BUF];
			read(self_pipe[0],&tmp,PIPE_BUF);	//empty the self pipe

			struct LaunchedTask *current = lth->head;
			while(current != NULL) {							//for all launched tasks
				int status;
				int zombie = waitpid(current->pid,&status,WNOHANG);			//check if they have exited
				uint8_t return_value = 0; 

				
				if(zombie) {								//if the task terminated

					
					if(WIFEXITED(status)) {
						return_value = WEXITSTATUS(status);
						writeTaskExitCode(return_value,current->id);	
					}
					

					struct task *tmp = getTaskByID(listTaskHead,current->id);
					tmp->canRun = 1;						//allow to run on the next minute
					removeLaunchedTask(lth,current);				//remove it from the launched tasks
					current = lth->head;
					continue;							//contiue browsing
				}		
				current = current->next;
			}
		}

		struct task *current = listTaskHead->premier;
		while(current != NULL) {									//for all taskss
			if(task_should_run(current) && (time(NULL) % 60) == 0 && current->canRun) {		//if they can and should run
				int task_pid = execute_task(current);						//execute it

				struct LaunchedTask *t = malloc(sizeof(struct LaunchedTask));			
				t->id = current->id;
				t->pid = task_pid;
				t->next = NULL;

				addLaunchedTask(lth,t);								//add it to the list
				current->canRun = 0;								//forbid it to run on the next minute
			}
			current = current->next;
		}

		while(time(NULL) % 60 == 0)									//wait until the next second	(probably not the cleanest way to do it)
			sleep(1);
	}
	

terminate:					//if the daemon must terminate
	close(self_pipe[0]);
	close(self_pipe[1]);
	close(req_fd);
	free(listTaskHead);
	return EXIT_SUCCESS;
	
error:						//if an error in encountered
	close(self_pipe[0]);
	close(self_pipe[1]);
	close(req_fd);
	return EXIT_FAILURE;
}
