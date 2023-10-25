#include "utils/read_responses.h"
#include "timing-text-io.h"
#include "utils/command_line.h"
#include "timing.h"

int read_rm_resp(int fd) {
	uint16_t code;

	// get REPTYPE
	int res = 0;
	res = read(fd, &code, 2);
	assert(res == 2);

	// convert code from bigendian
	code = be16toh(code);

	// check what is the good kind of error
	switch(code) {
		case 0x4552:	//ERROR
			// Print error code (we don't evaluate the error code because the only case is this one)
			printf("there is no task with that ID");
			break;

		case 0x4F4b:	//OK
			break;
	}
	return 0;
}

int read_cr_resp(int fd) {

	int count;
	uint16_t reptype;
	uint64_t taskId;
	char buf[sizeof(reptype) + sizeof(taskId)];
	count = read(fd, buf, sizeof(reptype)+sizeof(taskId));

	if(count != (sizeof(reptype) + sizeof(taskId))){
		return -1; //on a pas tout lu;
		}
	memmove(&reptype, buf, sizeof(reptype));
	memmove(&taskId, buf+sizeof(reptype), sizeof(taskId));
	printf("%ld", be64toh(taskId));
	return count;
}

int read_ls_resp(int fd) {

		uint16_t reptype;
		uint32_t nbtasks;

		read(fd, &reptype, 2);
		read(fd, &nbtasks, 4);

		for(int i = 0; i < be32toh(nbtasks); i++){

			uint64_t taskId;
			uint64_t minutes;
		  uint32_t hours;
		  uint8_t daysofweek;

			read(fd, &taskId, sizeof(taskId));
			read(fd, &minutes, sizeof(minutes));
			read(fd, &hours, sizeof(hours));
			read(fd, &daysofweek, sizeof(daysofweek));

			struct timing time= {.minutes=be64toh(minutes), .hours=be32toh(hours), .daysofweek=daysofweek};
			char buff[1024];

			timing_string_from_timing(buff, &time);

			uint32_t argc;
			read(fd, &argc, sizeof(argc));

			printf("%ld: ", be64toh(taskId));
			printf("%s ", buff);
			for(int ag = 0; ag < be32toh(argc); ag++){
				uint32_t length;
				read(fd, &length, sizeof(length));
				char data[be32toh(length)];
				read(fd, data, be32toh(length));
				printf("%.*s ", be32toh(length), data);
			}
			printf("\n");
	}
	return 0;
	}


int read_stderr_stdout_resp(int fd) {
	uint16_t code;
	uint16_t error_code;
	uint32_t output;

	// get REPTYPE
	int res = 0;
	res = read(fd, &code, 2);
	assert(res == 2);

	// convert code from bigendian
	code = be16toh(code);

	int output_length = 0;

	// check what is the good kind of error
	switch(code) {
		case 0x4552:	//ERROR
			// Read error_code
			res = read(fd,&error_code,2);
			assert(res == 2);
			error_code = be16toh(error_code);
			eval_error_type(fd, error_code);

			return 1;
			break;
		case 0x4F4b:	//OK
			// read output to print
			res = read(fd, &output, sizeof(uint32_t));
			// define output length
			output_length = be32toh(output);

			assert(res == sizeof(uint32_t));

			// new buffer to write the output
			char buf[output_length];

			res = read(fd, buf, output_length);
			//assert(res == output_length);
			// write output on STDOUT
			write(STDOUT_FILENO, buf, output_length);

			break;
	}
	return 0;
}

void eval_error_type(int fd, uint16_t error_code) {
	switch(error_code) {
		// two error cases
		case 0x4e46:
			printf("Error: There is no task with that ID\n");
			break;
		case 0x4e52:
			printf("Error: this task hasn't been executed\n");
			break;
		// OK case
		case 0x4F4b: break;
	}
}

int read_tm_resp(int fd) {
	uint16_t code;

	// get REPTYPE
	int res = 0;
	res = read(fd, &code, 2);
	assert(res == 2);
	return 0;

}

int read_tx_resp(int fd) {
	uint16_t code;
	uint32_t exec_number;
	uint64_t runtime;
	uint16_t exitcode;
	uint16_t error_code;

	int res = 0;

	res = read(fd,&code,2);		//read the response type
	assert(res == 2);
	code = be16toh(code);		//convert to the host endianness

	switch(code) {
		case 0x4552:	//ERROR
			res = read(fd,&error_code,2);				//empty the pipe
			assert(res == 2);
			printf("Error: There is no task with that ID\n");	//display an error message
			return 1;
		case 0x4F4b:	//OK
			break;
	}

	//If the task exists
	res = read(fd,&exec_number,4);		//read the number of time it was ran
	assert(res == 4);
	exec_number = be32toh(exec_number);

	for(int i=0;i<exec_number;i++) {		//for each task
		res = read(fd,&runtime,8);		//read the time it was ran at
		assert(res == 8);

		res = read(fd,&exitcode,2);		//read its exitcode
		assert(res == 2);

		time_t time = be64toh(runtime);
		char buf[25];
		strftime(buf,25,"%Y-%m-%d %H:%M:%S",localtime(&time));		//format the runtime to something readable
		printf("%s %d\n",buf, be16toh(exitcode));


	}
	return 0;
}
