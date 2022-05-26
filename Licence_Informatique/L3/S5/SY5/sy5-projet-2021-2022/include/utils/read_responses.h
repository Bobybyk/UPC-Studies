#ifndef READ_RESPONSES_H
#define READ_RESPONSES_H

/*git
 *	Maybe some unused include, juste a copy of requests.h includes here
 */

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
#include <time.h>
#include <stdint.h>

/*
 *	All response parsing functions should return 0 as the daemon should not send malformed responses
 */

int read_rm_resp(int fd);
int read_cr_resp(int fd);
int read_ls_resp(int fd);
int read_tx_resp(int fd);
int read_stderr_stdout_resp(int fd);
void eval_error_type(int fd, uint16_t error_code);
int read_tm_resp(int fd);

#endif
