#include "utils/send_requests.h"
#include "timing-text-io.h"

int send_ls_req(int fd) {
	uint16_t op_code = htobe16(CLIENT_REQUEST_LIST_TASKS);
	int count = write(fd,&op_code,sizeof(op_code));
	return count == sizeof(op_code) ? 0 : -1;
}

int send_cr_req(int fd, char *minutes, char *hours, char *days, int argc, char **argv) {

	uint16_t op_code = htobe16(CLIENT_REQUEST_CREATE_TASK);

	struct commandline cmd;					//create a commandline and initialize it with the given command
	int err = create_commandline(&cmd,argc,argv);
	assert(err == 0);

	struct timing t;					//create a timing and fill it with the specified fields
	err = timing_from_strings(&t,minutes,hours,days);
	assert(err == 0);

	int buf_size = sizeof(uint16_t)/*op_code*/ + sizeof(uint64_t)/*minutes*/ + sizeof(uint32_t)/*hours*/ + sizeof(uint8_t)/*days*/ + sizeof(uint32_t)/*argc*/;

	for(int i=0;i<argc;i++) {
		buf_size += sizeof(uint32_t) + strlen(argv[i]);		//add the size of each string to the buffer size
	}

	char buf[buf_size];

	int p = 0;					//write the op code
	memmove(buf,&op_code,2);
	p+=2;

	p+= format_from_timing(buf+p,&t);

	uint32_t arg_count = htobe32(argc);		//write the argc field
	memmove(buf+p,&arg_count,4);
	p+=4;

	for(int i=0;i<argc;i++) {			//write the string fields
		p += format_from_string(buf+p,cmd.ARGV[i]);		//for each string in cmd write it in buf and add the size to p
		free(cmd.ARGV[i]->data);				//free the string data
		free(cmd.ARGV[i]);					//free the string
	}
	free(cmd.ARGV);							//free the string array

	int count = write(fd,buf,p);					//write the buffer

	return count == p ? 0 : -1;
}

int send_tm_req(int fd) {
	uint16_t op_code = htobe16(CLIENT_REQUEST_TERMINATE);
	int count = write(fd,&op_code,sizeof(op_code));
	return count == sizeof(op_code) ? 0 : -1;
}

/*
 *
 * quatre fonctions suivantes à la structure sensiblement similaire
 * seul la macro change en fonction du message à écrire
 *
 */

int send_rm_req(int fd, uint64_t taskid) {
	
	// traduction en big endian
	uint16_t op_code = htobe16(CLIENT_REQUEST_REMOVE_TASK);
	uint64_t big_taskid = htobe64(taskid);
	
	// création d'un buffer pour préparer le messager écrit sur le pipe 
	char buf[sizeof(taskid)+sizeof(op_code)]; // 16/8 + 64/8
	
	// concaténation des deux paramètres dans le buffer
	memmove(buf, &op_code, sizeof(op_code));
	memmove(buf+sizeof(op_code), &big_taskid, sizeof(taskid));
	
	// écriture sur le pipe 
	int count = write(fd, &buf, sizeof(buf));

	// retour réussite ou erreur
	return count > 0 ? 0 : -1;
}

int send_tx_req(int fd, uint64_t taskid) {

	// traduction en big endian
	uint16_t op_code = htobe16(CLIENT_REQUEST_GET_TIMES_AND_EXITCODES);
	uint64_t big_taskid = htobe64(taskid);
	
	// création d'un buffer pour préparer le messager écrit sur le pipe 
	char buf[sizeof(taskid)+sizeof(op_code)]; // 16/8 + 64/8
	
	// concaténation des deux paramètres dans le buffer
	memmove(buf, &op_code, sizeof(op_code));
	memmove(buf+sizeof(op_code), &big_taskid, sizeof(taskid));
	
	// écriture sur le pipe 
	int count = write(fd, &buf, sizeof(buf));
	
	// retour réussite ou erreur
	return count > 0 ? 0 : -1;
}

int send_stdout_req(int fd, uint64_t taskid) {
	uint16_t op_code = htobe16(CLIENT_REQUEST_GET_STDOUT);
	uint64_t big_taskid = htobe64(taskid);
	// BUFF
	char buf[sizeof(taskid)+sizeof(op_code)]; // 16/8 + 64/8
	// filling
	memmove(buf, &op_code, sizeof(op_code));
	memmove(buf+sizeof(op_code), &big_taskid, sizeof(taskid));
	// writing
	int count = write(fd, &buf, sizeof(buf));
	// return
	return count > 0 ? 0 : -1;
}

int send_stder_req(int fd, uint64_t taskid) {
	uint16_t op_code = htobe16(CLIENT_REQUEST_GET_STDERR);
	uint64_t big_taskid = htobe64(taskid);
	// BUFF
	char buf[sizeof(taskid)+sizeof(op_code)]; // 16/8 + 64/8
	// filling
	memmove(buf, &op_code, sizeof(op_code));
	memmove(buf+sizeof(op_code), &big_taskid, sizeof(taskid));
	// writing
	int count = write(fd, &buf, sizeof(buf));
	// return
	return count > 0 ? 0 : -1;
}
