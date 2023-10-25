#include "utils/read_requests.h"

int read_request(int fd, Liste *listTaskHead) {

	
	char buf[PIPE_BUF];
	int ret = read(fd,buf,PIPE_BUF);
	if(ret < 0)
		return -1;
	uint16_t opcode;
	memcpy(&opcode,buf,2);
	opcode = be16toh(opcode);

	char *user = getlogin();
	char res_path[strlen("/tmp/") + strlen(user) + strlen("/saturnd/pipes/saturnd-reply-pipe") + 1];
	sprintf(res_path,"%s%s%s","/tmp/",user,"/saturnd/pipes/saturnd-reply-pipe");

	int res_fd = open(res_path,O_WRONLY);



	switch(opcode) {
		case CLIENT_REQUEST_LIST_TASKS:

			send_ls_response(res_fd);	//not yet written
			break;
		case CLIENT_REQUEST_CREATE_TASK:
			{
				
				struct timing *t = malloc(sizeof(struct timing));
				memcpy(t,buf+2,13);
				char **cmd = arg_array_from_buf(buf+15);
				struct task *task = NULL;
				int task_id = create_task(listTaskHead, t,cmd,task);	

				int ret = send_cr_response(res_fd, task_id);
				if(ret < 0)
					return -1;	
				close(res_fd);

			}
			break;
		case CLIENT_REQUEST_REMOVE_TASK: {
			uint64_t taskid;
			memcpy(&taskid,buf+2,sizeof(taskid));
			int reponseTask = delete_task(listTaskHead, be64toh(taskid));		
			int reponse = send_rm_response(res_fd, reponseTask);	//not yet written
			assert(reponse >= 0);
					}
			break;
		case CLIENT_REQUEST_GET_TIMES_AND_EXITCODES: {
			uint64_t taskid;
			memcpy(&taskid,buf+2, sizeof(taskid));
			
			size_t taskIdHost = be64toh(taskid);
			int reponseTask = task_exist(listTaskHead, taskIdHost);
			int reponse = send_tx_response(res_fd, reponseTask, taskIdHost);
			
			assert(reponse >= 0);
			break;
							     }
		case CLIENT_REQUEST_GET_STDOUT:
			{ 
			uint64_t task_id;
			memcpy(&task_id, buf+2, sizeof(task_id));
			task_id = be64toh(task_id);
			char task_path[strlen("tasks/") + sizeof(task_id) + strlen("/stdout") + 1];
			sprintf(task_path,"%s%ld%s","tasks/",task_id,"/stdout");
			int task_fd = open(task_path, O_RDONLY);
			send_so_response(res_fd, task_fd);
			}
			break;
		case CLIENT_REQUEST_GET_STDERR:
			{ 
			uint64_t task_id;
			memcpy(&task_id, buf+2, sizeof(task_id));
			task_id = be64toh(task_id);
			char task_path[strlen("tasks/") + sizeof(task_id) + 1];
			sprintf(task_path,"%s%ld","tasks/",task_id);
			send_se_response(res_fd, task_path);
			}
			break;
		case CLIENT_REQUEST_TERMINATE:
			send_tm_response(res_fd);
			close(res_fd);
			return 1;
	}

	close(res_fd);
	return 0;
}

