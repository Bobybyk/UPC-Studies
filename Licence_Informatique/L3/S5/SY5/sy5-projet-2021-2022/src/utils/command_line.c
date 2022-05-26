#include "utils/command_line.h"
#include "utils/custom_string.h"
#include <assert.h>


int create_commandline(struct commandline *command, int argc, char *argv[]){

	if(argv == NULL){ //TODO: verifier arguments vide
		return -1;
	}

	int rep; //retient les erreurs possibles
	struct custom_string *stringcustom; //creation d'un object custom_string
	struct custom_string **p = malloc(sizeof(struct custom_string)*argc); // création d'un tableau de custom_string
	assert(p != NULL);

	for (int i = 0; i < argc; i++){ //pour tous les agurments présent dans argv
		stringcustom = malloc(sizeof(struct custom_string)); //allocation en mémoire
		assert(stringcustom != NULL);
		rep = create_custom_string(stringcustom , argv[i]); //creation d'un string_custom à partir d'un élément du tableau argv (string basique vers string custom)
		if(rep == -1){
			return -1;
		}
		p[i] = stringcustom;
	}
	//initlisation de la structure
	command->ARGC = htobe32(argc);
	command->ARGV = p;

	return 0;
}

char **arg_array_from_buf(char *buf) {
	int padding = 0;

	uint32_t argc;
	memcpy(&argc,buf+padding,4);
	padding += 4;
	argc = be32toh(argc);

	char **argv;
	argv = malloc(sizeof(char*) * (argc + 1));

	for(int i=0;i<argc;i++) {
		uint32_t arg_len;
		memcpy(&arg_len,buf+padding,4);
		padding += 4;
		arg_len = be32toh(arg_len);

		argv[i] = calloc(arg_len+1,1);

		char arg_tmp[arg_len];
		memcpy(arg_tmp,buf+padding,arg_len);
		memset(arg_tmp+arg_len,0,1);
		padding += arg_len;
		memcpy(argv[i],arg_tmp,arg_len);
	}
	argv[argc] = (char*) NULL;

	return argv;
}
