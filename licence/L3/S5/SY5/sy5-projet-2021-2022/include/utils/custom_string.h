#ifndef CUSTOM_STRING_H
#define CUSTOM_STRING_H

#define MAX_COMMAND_LENGTH 256

#include <string.h>
#include <stdint.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <assert.h>

struct custom_string {
	uint32_t length;
	char *data;
};

/*
 *	The memory occupied by this structure is: "sizeof(uint32_t) + be32toh(length)"
 */


int create_custom_string(struct custom_string *dest, char *str);		
/*
 * This function initializes a struct custom_string from a char array
 * Returns 0 on success, -1 on error
 */


int format_from_string(char *dest, struct custom_string *str);			
/*
 * This function writes a formatted custom_string into the buffer 
 * Returns the number of bytes written on success, -1 on error
 */

void print_custom_string(struct custom_string * string);
/*
 * This function display the string located in the
 * struct custom_string *
 */

#endif
