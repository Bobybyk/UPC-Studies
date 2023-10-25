#ifndef SEND_REQUESTS_H
#define SEND_REQUESTS_H

#include "client-request.h"
#include <unistd.h>
#include <endian.h>
#include <stdlib.h>
#include <stdint.h>
#include "utils/custom_string.h"
#include "utils/command_line.h"
#include "timing.h"
#include "timing-text-io.h"
#include <stdio.h>
#include <string.h>

/*
 *	All request functions return 0 on success and -1 on error
 */

int send_ls_req(int fd);	//to list the tasks
int send_tm_req(int fd);	//to terminate the daemon
int send_cr_req(int fd, char *minutes, char *hours, char *days, int argc, char **argv); 
int send_stdout_req(int fd, uint64_t taskid);
int send_stder_req(int fd, uint64_t taskid);
int send_tx_req(int fd, uint64_t taskid);
int send_rm_req(int fd, uint64_t taskid);

#endif
