#include "utils/custom_string.h"

int create_custom_string(struct custom_string *dest, char *str) {
	uint32_t len = strlen(str);					//get str length
	if(len <= 0)							//check for error
		return -1;
	dest->length = htobe32(len);					//write length into struct
	dest->data = malloc(len);
	strncpy(dest->data,str,len);
	assert(dest != NULL);

	return 0;
}

int format_from_string(char *dest, struct custom_string *str) {
	memmove(dest,&str->length,sizeof(uint32_t));			//write the length of the string
	if(dest == NULL)						//check for error
		return -1;
	strncpy(dest+4,str->data,be32toh(str->length));
	if(dest+4 == NULL)						//check for error
		return -1;
	return sizeof(uint32_t) + be32toh(str->length);
}

// to print a custom string 
void print_custom_string(struct custom_string * string) {
	int string_size = be32toh(string->length);
	for (int i = 0 ; i<string_size ; i++) {
		printf("%c", string->data[i]);
	}
}
