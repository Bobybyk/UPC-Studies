#include "daemon.h"

void daemonize(void) {
	
	switch(fork()) {			//fork to create the daemon
		case -1:	//error
			perror("Fork");
			break;
		case 0:		//child
			break;
		default:	//parent
			exit(EXIT_SUCCESS);		//let the parent terminate
			break;
	}
	setsid();				//create a session for the process
	
	for(int i=0;i<FOPEN_MAX;i++)
		close(i);

	int pid = getpid();			//write the process' pid in a file to be able to identify easily
	int pidFile = open("saturnd.pid",O_WRONLY | O_TRUNC | O_CREAT, 0644);
	dprintf(pidFile,"%d",pid);
	close(pidFile);
}
